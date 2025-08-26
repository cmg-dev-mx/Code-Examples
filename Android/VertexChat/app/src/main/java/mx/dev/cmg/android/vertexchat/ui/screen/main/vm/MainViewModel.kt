package mx.dev.cmg.android.vertexchat.ui.screen.main.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.vertexchat.core.model.MessageItem
import mx.dev.cmg.android.vertexchat.core.usecase.ChatUseCase
import mx.dev.cmg.android.vertexchat.core.usecase.QueryPromptUseCase
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: QueryPromptUseCase,
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

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

            UiEvent.OnStartChat -> {
                startChat()
            }

            UiEvent.OnStopChat -> {
                stopChat()
            }
        }
    }

    private fun startChat() {
        viewModelScope.launch {
            chatUseCase.startChat()
            uiState = uiState.copy(
                listeningState = InputSate.IDLE,
                chatting = true
            )
        }
    }

    private fun stopChat() {
        viewModelScope.launch {
            chatUseCase.stopChat()
            uiState = uiState.copy(
                conversation = emptyList(),
                confirmation = "",
                listeningState = InputSate.IDLE,
                chatting = false
            )
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
            viewModelScope.launch {
                uiState = uiState.copy(
                    listeningState = InputSate.LOADING
                )

                val prompt = uiState.text
                val newMessage = MessageItem(
                    timeStamp = System.currentTimeMillis(),
                    message = prompt,
                    isUser = true
                )

                uiState = uiState.copy(
                    conversation = uiState.conversation + newMessage,
                    text = "",
                )

                useCase.invoke(prompt).collect {
                    val response = it

                    if (response.startsWith("DATA:")) {
                        val data = response.substringAfter("DATA:")
                        uiState = uiState.copy(
                            confirmation = data,
                            conversation = emptyList(),
                            listeningState = InputSate.IDLE
                        )

                        delay(10.seconds)

                        uiState = uiState.copy(
                            confirmation = ""
                        )

                    } else {

                        val newMessage = MessageItem(
                            timeStamp = System.currentTimeMillis(),
                            message = response,
                            isUser = false
                        )

                        uiState = uiState.copy(
                            conversation = uiState.conversation + newMessage,
                            listeningState = InputSate.IDLE,
                        )
                    }
                }
            }
        }
    }

    private fun startListening() {
        uiState = uiState.copy(
            listeningState = InputSate.LISTENING,
            chatting = false
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
    val conversation: List<MessageItem> = emptyList(),
    val confirmation: String = "",
    val chatting: Boolean = false
)

sealed class UiEvent {
    data class OnTextChange(val text: String) : UiEvent()
    object OnSendClick : UiEvent()
    object OnStartListening : UiEvent()
    object OnStopListening : UiEvent()
    object OnStartChat : UiEvent()
    object OnStopChat : UiEvent()
}

enum class InputSate {
    IDLE,
    LISTENING,
    LOADING,
    SEND
}
