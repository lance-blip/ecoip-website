package za.co.quikle.lexio.domain.model

import java.util.UUID

data class LegalCitation(
    val id: String = UUID.randomUUID().toString(),
    val actName: String,           // e.g., "Labour Relations Act"
    val actNumber: String,         // e.g., "Act 66 of 1995"
    val section: String,           // e.g., "Section 188"
    val subsection: String? = null,// e.g., "Subsection (1)(a)"
    val fullReference: String,     // e.g., "LRA s188(1)(a)"
    val shortReference: String,    // e.g., "LRA s188"
    val fullText: String? = null   // The actual text of the section
)
