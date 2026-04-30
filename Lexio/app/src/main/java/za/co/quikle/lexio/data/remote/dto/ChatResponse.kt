package za.co.quikle.lexio.data.remote.dto

data class ChatResponse(
    val message: String,
    val citations: List<CitationDto>,
    val confidence: String,
    val suggestedFollowUps: List<String>,
    val conversationId: String
)

data class CitationDto(
    val actName: String,
    val actNumber: String,
    val section: String,
    val subsection: String?,
    val fullReference: String,
    val shortReference: String,
    val fullText: String?
)
