package mx.dev.cmg.android.vertexchat.ui.screen.main.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.dev.cmg.android.vertexchat.core.model.MessageItem
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnTextChange -> updateTextInInput(event)

            is UiEvent.OnSendClick -> queryPrompt()

            UiEvent.OnStartListening -> {
                startListening()
            }

            UiEvent.OnStopListening -> {
                stopListening()
            }
        }
    }

    private fun updateTextInInput(event: UiEvent.OnTextChange) {
        uiState = uiState.copy(
            text = event.text,
            listeningState = if (event.text.isBlank()) {
                InputSate.IDLE
            } else {
                InputSate.SEND
            }
        )
    }

    private fun queryPrompt() {
        if (uiState.text.isNotBlank()) {

            uiState = uiState.copy(
                listeningState = InputSate.LOADING
            )

            val newMessage = MessageItem(
                timeStamp = System.currentTimeMillis(),
                message = uiState.text,
                isUser = true
            )
            uiState = uiState.copy(
                conversation = uiState.conversation + newMessage,
                text = "",
                listeningState = InputSate.IDLE
            )
        }
    }

    private fun startListening() {
        uiState = uiState.copy(
            listeningState = InputSate.LISTENING
        )
    }

    private fun stopListening() {
        uiState = uiState.copy(
            listeningState = InputSate.IDLE
        )
    }
}

data class MainUiState(
    val listeningState: InputSate = InputSate.IDLE,
    val text: String = "",
    val conversation: List<MessageItem> = emptyList()
)

sealed class UiEvent {
    data class OnTextChange(val text: String): UiEvent()
    object OnSendClick: UiEvent()
    object OnStartListening: UiEvent()
    object OnStopListening: UiEvent()
}

enum class InputSate {
    IDLE,
    LISTENING,
    LOADING,
    SEND
}
