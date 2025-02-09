package mx.dev.cmg.android.jetai.chatroom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.models.ChatRoom
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.ChatRepository
import mx.dev.cmg.android.jetai.utils.ext.collectAndHandle

class ChatRoomViewModel(
    private val chatRepository: ChatRepository = Graph.chatRepository,
    private val authRepository: AuthRepository = Graph.authRepository
) : ViewModel() {
    var chatRoomState by mutableStateOf(ChatRoomState())
        private set

    init {
        viewModelScope.launch {
            authRepository.currentUser.collectLatest {
                chatRoomState = chatRoomState.copy(currentUser = it)
            }
        }
    }

    fun initChatRoom() {
        viewModelScope.launch {
            chatRepository.getChatRoomList().collectAndHandle(
                onError = {
                    chatRoomState = chatRoomState.copy(errorMsg = it?.message, loading = false)
                },
                onLoading = {
                    chatRoomState = chatRoomState.copy(errorMsg = null, loading = true)
                }
            ) { chatRooms ->
                chatRoomState =
                    chatRoomState.copy(chatRooms = chatRooms, loading = false, errorMsg = null)
            }
        }
    }

    fun resetChatId() {
        chatRoomState = chatRoomState.copy(newChatId = null)
    }

    fun newChatRoom() {
        viewModelScope.launch {
            val chatId = chatRepository.createChatRoom()
            chatRoomState = chatRoomState.copy(
                newChatId = chatId
            )
        }
    }
}

data class ChatRoomState(
    val chatRooms: List<ChatRoom> = emptyList(),
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val newChatId: String? = null,
    val currentUser: FirebaseUser? = null,

    )