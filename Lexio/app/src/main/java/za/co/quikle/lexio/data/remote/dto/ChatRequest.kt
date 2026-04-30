package za.co.quikle.lexio.data.remote.dto

data class ChatRequest(
    val message: String,
    val conversationId: String? = null,
    val language: String = "en"
)
