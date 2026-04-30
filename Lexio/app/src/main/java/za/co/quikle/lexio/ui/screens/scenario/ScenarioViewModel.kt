package za.co.quikle.lexio.ui.screens.scenario

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ScenarioUiState(
    val scenarioInput: String = "",
    val isAnalysing: Boolean = false,
    val hasResult: Boolean = false
)

class ScenarioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScenarioUiState())
    val uiState: StateFlow<ScenarioUiState> = _uiState.asStateFlow()

    fun onInputChanged(input: String) {
        _uiState.value = _uiState.value.copy(scenarioInput = input)
    }

    fun analyseScenario() {
        // Placeholder — will be implemented with API integration
    }
}
