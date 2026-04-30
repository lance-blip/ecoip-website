package za.co.quikle.lexio.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import za.co.quikle.lexio.data.remote.dto.ChatRequest
import za.co.quikle.lexio.data.remote.dto.ChatResponse
import za.co.quikle.lexio.data.remote.dto.ScenarioRequest
import za.co.quikle.lexio.data.remote.dto.ScenarioResponse

interface ApiService {

    @POST("api/chat")
    suspend fun sendChatMessage(
        @Body request: ChatRequest
    ): Response<ChatResponse>

    @POST("api/scenario")
    suspend fun analyseScenario(
        @Body request: ScenarioRequest
    ): Response<ScenarioResponse>
}
