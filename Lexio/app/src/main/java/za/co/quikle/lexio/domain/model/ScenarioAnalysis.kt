package za.co.quikle.lexio.domain.model

import java.util.UUID

data class ScenarioAnalysis(
    val id: String = UUID.randomUUID().toString(),
    val userScenario: String,
    val applicableLaws: List<ApplicableLaw>,
    val analysis: String,
    val potentialViolations: List<String>,
    val rights: List<String>,
    val nextSteps: List<NextStep>,
    val timestamp: Long = System.currentTimeMillis()
)

data class ApplicableLaw(
    val actName: String,
    val sections: List<String>,
    val relevance: String
)

data class NextStep(
    val action: String,
    val description: String,
    val contactInfo: String? = null,  // e.g., "CCMA: 0861 16 16 16"
    val url: String? = null
)
