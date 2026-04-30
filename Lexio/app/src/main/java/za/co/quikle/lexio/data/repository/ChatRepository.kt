package za.co.quikle.lexio.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.quikle.lexio.data.local.dao.ConversationDao
import za.co.quikle.lexio.data.local.entity.ConversationEntity
import za.co.quikle.lexio.data.local.entity.MessageEntity
import za.co.quikle.lexio.data.remote.ApiService
import za.co.quikle.lexio.data.remote.dto.ChatRequest
import za.co.quikle.lexio.domain.model.ChatMessage
import za.co.quikle.lexio.domain.model.Confidence
import za.co.quikle.lexio.domain.model.LegalCitation
import java.util.UUID

class ChatRepository(
    private val apiService: ApiService,
    private val conversationDao: ConversationDao
) {
    private val gson = Gson()

    fun getConversations(): Flow<List<ConversationEntity>> {
        return conversationDao.getAllConversations()
    }

    fun getMessages(conversationId: String): Flow<List<ChatMessage>> {
        return conversationDao.getMessagesForConversation(conversationId).map { entities ->
            entities.map { entity ->
                val citations = try {
                    gson.fromJson(entity.citationsJson, Array<LegalCitation>::class.java).toList()
                } catch (e: Exception) {
                    emptyList()
                }
                ChatMessage(
                    id = entity.id,
                    content = entity.content,
                    isUser = entity.isUser,
                    timestamp = entity.timestamp,
                    citations = citations,
                    confidence = try {
                        Confidence.valueOf(entity.confidence)
                    } catch (e: Exception) {
                        Confidence.HIGH
                    }
                )
            }
        }
    }

    suspend fun sendMessage(
        message: String,
        conversationId: String?
    ): Result<ChatMessage> {
        return try {
            val response = apiService.sendChatMessage(
                ChatRequest(
                    message = message,
                    conversationId = conversationId
                )
            )

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                val citations = body.citations.map { dto ->
                    LegalCitation(
                        actName = dto.actName,
                        actNumber = dto.actNumber,
                        section = dto.section,
                        subsection = dto.subsection,
                        fullReference = dto.fullReference,
                        shortReference = dto.shortReference,
                        fullText = dto.fullText
                    )
                }

                val chatMessage = ChatMessage(
                    content = body.message,
                    isUser = false,
                    citations = citations,
                    confidence = try {
                        Confidence.valueOf(body.confidence.uppercase())
                    } catch (e: Exception) {
                        Confidence.HIGH
                    },
                    suggestedFollowUps = body.suggestedFollowUps
                )

                // Cache message locally
                val activeConversationId = body.conversationId
                conversationDao.insertMessage(
                    MessageEntity(
                        id = chatMessage.id,
                        conversationId = activeConversationId,
                        content = chatMessage.content,
                        isUser = false,
                        timestamp = chatMessage.timestamp,
                        citationsJson = gson.toJson(citations),
                        confidence = chatMessage.confidence.name
                    )
                )

                Result.success(chatMessage)
            } else {
                Result.failure(Exception("API error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveUserMessage(message: String, conversationId: String) {
        val userMessage = MessageEntity(
            id = UUID.randomUUID().toString(),
            conversationId = conversationId,
            content = message,
            isUser = true,
            timestamp = System.currentTimeMillis(),
            citationsJson = "[]",
            confidence = Confidence.HIGH.name
        )
        conversationDao.insertMessage(userMessage)
    }

    suspend fun createConversation(title: String): String {
        val id = UUID.randomUUID().toString()
        val now = System.currentTimeMillis()
        conversationDao.insertConversation(
            ConversationEntity(
                id = id,
                title = title,
                createdAt = now,
                updatedAt = now
            )
        )
        return id
    }

    suspend fun deleteConversation(conversationId: String) {
        conversationDao.deleteConversationById(conversationId)
    }
}
