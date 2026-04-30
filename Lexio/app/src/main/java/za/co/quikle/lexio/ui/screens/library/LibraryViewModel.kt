package za.co.quikle.lexio.ui.screens.library

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import za.co.quikle.lexio.domain.model.RightsCategory

data class LibraryUiState(
    val categories: List<RightsCategory> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
}
