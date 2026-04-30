package za.co.quikle.lexio.data.repository

import za.co.quikle.lexio.data.remote.ApiService
import za.co.quikle.lexio.data.remote.dto.ScenarioRequest
import za.co.quikle.lexio.domain.model.ApplicableLaw
import za.co.quikle.lexio.domain.model.NextStep
import za.co.quikle.lexio.domain.model.ScenarioAnalysis

class ScenarioRepository(
    private val apiService: ApiService
) {
    suspend fun analyseScenario(scenario: String): Result<ScenarioAnalysis> {
        return try {
            val response = apiService.analyseScenario(
                ScenarioRequest(scenario = scenario)
            )

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                val analysis = ScenarioAnalysis(
                    userScenario = scenario,
                    applicableLaws = body.applicableLaws.map { dto ->
                        ApplicableLaw(
                            actName = dto.actName,
                            sections = dto.sections,
                            relevance = dto.relevance
                        )
                    },
                    analysis = body.analysis,
                    potentialViolations = body.potentialViolations,
                    rights = body.rights,
                    nextSteps = body.nextSteps.map { dto ->
                        NextStep(
                            action = dto.action,
                            description = dto.description,
                            contactInfo = dto.contactInfo,
                            url = dto.url
                        )
                    }
                )
                Result.success(analysis)
            } else {
                Result.failure(Exception("API error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
