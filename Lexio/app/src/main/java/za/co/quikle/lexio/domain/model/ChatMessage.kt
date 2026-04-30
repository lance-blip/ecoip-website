package za.co.quikle.lexio.domain.model

import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val citations: List<LegalCitation> = emptyList(),
    val confidence: Confidence = Confidence.HIGH,
    val suggestedFollowUps: List<String> = emptyList()
)

enum class Confidence {
    HIGH, MEDIUM, LOW
}
