package za.co.quikle.lexio.ui.screens.chat

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import za.co.quikle.lexio.domain.model.ChatMessage

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val currentInput: String = "",
    val conversationId: String? = null
)

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun onInputChanged(input: String) {
        _uiState.value = _uiState.value.copy(currentInput = input)
    }

    fun sendMessage() {
        // Placeholder — will be implemented with API integration
    }
}
