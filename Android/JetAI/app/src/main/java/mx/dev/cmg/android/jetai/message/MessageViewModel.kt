package mx.dev.cmg.android.jetai.message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.models.ChatMessage
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.ChatRepository
import mx.dev.cmg.android.jetai.utils.ext.collectAndHandle

class MessageViewModel(
    private val chatRepository: ChatRepository = Graph.chatRepository,
    private val authRepository: AuthRepository = Graph.authRepository,
    private val chatRoomId: String,
    private val chatRoomTitle: String,
) : ViewModel() {

    var chatState by mutableStateOf(ChatState())
        private set

    init {
        viewModelScope.launch {
            authRepository.currentUser.collect {
                chatState = chatState.copy(currentUser = it)
            }
        }
    }

    init {
        getMessages(chatRoomId)
    }

    init {
        viewModelScope.launch {
            chatRepository.fetchHistory(chatRoomId, chatRoomTitle)
        }
    }

    private fun getMessages(chatRoomId: String) {
        viewModelScope.launch {
            chatRepository.getMessage(chatRoomId).collectAndHandle {
                chatState = chatState.copy(messages = it)
            }
        }
    }

    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(userPrompt = userMessage, chatId = chatRoomId)
                .collectAndHandle(
                    onLoading = {
                        chatState = chatState.copy(isProcessingMessage = true)
                    },
                    onError = {
                        chatState = chatState.copy(isProcessingMessage = false)
                        it?.printStackTrace()
                    }
                ) {
                    chatState = chatState.copy(isProcessingMessage = false)
                }
        }
    }
}

data class ChatState(
    val messages: List<ChatMessage> = emptyList(),
    val isProcessingMessage: Boolean = false,
    val currentUser: FirebaseUser? = null
    )

@Suppress("UNCHECKED_CAST")
class MessageViewModelFactory(
    private val chatRoomId: String,
    private val chatRoomTitle: String,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MessageViewModel(chatRoomId = chatRoomId, chatRoomTitle = chatRoomTitle) as T
    }
}
