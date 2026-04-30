package za.co.quikle.lexio.domain.model

data class RightsTopic(
    val id: String,
    val title: String,                          // e.g., "Unfair Dismissal"
    val summary: String,                        // Plain-language 2-3 paragraph summary
    val legislation: List<LegalCitation>,
    val fullLegalText: String?,                 // Expandable actual legal text
    val relatedTopicIds: List<String> = emptyList()
)
