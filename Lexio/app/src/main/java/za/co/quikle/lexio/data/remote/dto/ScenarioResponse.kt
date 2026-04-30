package za.co.quikle.lexio.data.remote.dto

data class ScenarioResponse(
    val analysis: String,
    val applicableLaws: List<ApplicableLawDto>,
    val potentialViolations: List<String>,
    val rights: List<String>,
    val nextSteps: List<NextStepDto>
)

data class ApplicableLawDto(
    val actName: String,
    val sections: List<String>,
    val relevance: String
)

data class NextStepDto(
    val action: String,
    val description: String,
    val contactInfo: String?,
    val url: String?
)
