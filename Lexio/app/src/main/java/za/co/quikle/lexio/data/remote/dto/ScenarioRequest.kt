package za.co.quikle.lexio.data.remote.dto

data class ScenarioRequest(
    val scenario: String,
    val language: String = "en"
)
