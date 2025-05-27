package mx.dev.cmg.android.geminiai.ui.screen.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.geminiai.model.MessageItem
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

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
            val chatItem = MessageItem(
                id = System.currentTimeMillis(),
                message = _uiState.value.text,
                isUser = true
            )

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                conversation = _uiState.value.conversation + chatItem,
                text = ""
            )

            delay(2.seconds)

            val responseItem = MessageItem(
                id = System.currentTimeMillis(),
                message = "This is a simulated response to: ${chatItem.message}",
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

