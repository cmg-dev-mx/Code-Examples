package mx.dev.cmg.android.jetai.chatroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.dev.cmg.android.jetai.authentication.register.defaultPadding
import mx.dev.cmg.android.jetai.authentication.register.itemSpacing
import mx.dev.cmg.android.jetai.data.models.ChatRoom
import mx.dev.cmg.android.jetai.utils.formatDate

const val EMPTY_TITLE = "empty_title"

@Composable
fun ChatRoomScreen(
    modifier: Modifier = Modifier,
    chatRoomViewModel: ChatRoomViewModel = viewModel(),
    onNavigateToMsgScreen: (id: String, chatTitle: String) -> Unit
) {
    val chatRoomState = chatRoomViewModel.chatRoomState

    LaunchedEffect(true) {
        chatRoomViewModel.initChatRoom()
    }

    LaunchedEffect(chatRoomState.newChatId) {
        if (chatRoomState.newChatId != null) {
            onNavigateToMsgScreen(chatRoomState.newChatId, EMPTY_TITLE)
            chatRoomViewModel.resetChatId()
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            AnimatedVisibility(visible = chatRoomState.loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }

        items(chatRoomState.chatRooms) {
            ChatRoomItem(
                chatRoom = it,
                onClick = {
                    onNavigateToMsgScreen(it.id, it.title)
                }
            )
            Spacer(modifier = Modifier.height(itemSpacing))
        }
    }
}

@Composable
fun ChatRoomItem(
    modifier: Modifier = Modifier,
    chatRoom: ChatRoom,
    onClick:() -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(defaultPadding)
        ) {
            Text(
                text = chatRoom.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(itemSpacing))
            Text(
                text = formatDate(chatRoom.timestamp.toDate()),
                fontWeight = FontWeight.Light
            )
        }
    }
}