package mx.dev.cmg.android.geminiai.ui.screen.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.dev.cmg.android.geminiai.model.MessageItem
import mx.dev.cmg.android.geminiai.source.ai.AISource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val source: AISource
) : ViewModel() {

    private val _uiState = mutableStateOf(MainUIState())
    val uiState: State<MainUIState> = _uiState

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnInputValueChanged -> {
                updateText(event.newText)
            }
            is MainEvent.OnListenEvent -> {
                _uiState.value = _uiState.value.copy(
                    isListening = !_uiState.value.isListening
                )
            }
            is MainEvent.OnSendEvent -> {
                askConversation()
            }
        }
    }

    private fun updateText(value: String) {
        _uiState.value = _uiState.value.copy(
            text = value
        )
    }

    private fun askConversation() {
        viewModelScope.launch {
            val message = _uiState.value.text.trim()

            val chatItem = MessageItem(
                id = System.currentTimeMillis(),
                message = message,
                isUser = true
            )

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                conversation = _uiState.value.conversation + chatItem,
                text = ""
            )

            val response = source.askQuestion(message)

            val responseItem = MessageItem(
                id = System.currentTimeMillis(),
                message = response,
                isUser = false
            )

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                conversation = _uiState.value.conversation + responseItem,
                isListening = false
            )
        }
    }
}

sealed class MainEvent {
    data class OnInputValueChanged(val newText: String) : MainEvent()
    data object OnListenEvent : MainEvent()
    data object OnSendEvent : MainEvent()
}

data class MainUIState(
    val isLoading: Boolean = false,
    val isListening: Boolean = false,
    val text: String = "",
    val conversation: List<MessageItem> = emptyList()
)

