package mx.dev.cmg.android.geminiai.ui.screen.main.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev.cmg.android.geminiai.R
import mx.dev.cmg.android.geminiai.model.MessageItem
import mx.dev.cmg.android.geminiai.ui.screen.main.viewmodel.MainEvent
import mx.dev.cmg.android.geminiai.ui.screen.main.viewmodel.MainViewModel
import mx.dev.cmg.android.geminiai.ui.theme.GeminiAITheme

@Preview
@Composable
private fun MainLayoutPreview() {
    val conversation = listOf(
        MessageItem(id = 1, message = "Hello, how can I help you?", isUser = false),
        MessageItem(id = 2, message = "I need assistance with my account.", isUser = true)
    )

    GeminiAITheme {
        MainContainer(
            conversation = conversation,
        )
    }
}

@Composable
fun MainLayout() {

    val vm = hiltViewModel<MainViewModel>()
    val state = vm.uiState.value

    MainContainer(
        modifier = Modifier.fillMaxSize(),
        value = state.text,
        conversation = state.conversation,
        isLoading = state.isLoading,
        isListening = state.isListening,
        onValueChange = { vm.handleEvent(MainEvent.OnInputValueChanged(it)) },
        onSendInfo = { vm.handleEvent(MainEvent.OnSendEvent) },
        onClickMic = { vm.handleEvent(MainEvent.OnListenEvent) }
    )
}

@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    value: String = "",
    conversation: List<MessageItem> = emptyList(),
    isLoading: Boolean = false,
    isListening: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onSendInfo: () -> Unit = {},
    onClickMic: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
            .imePadding()
            .fillMaxSize(),
        topBar = {
            CustomTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        },
        bottomBar = {
            CustomBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = value,
                onValueChange = onValueChange,
                isListening = isListening,
                onSendInfo = onSendInfo,
                onClickMic = onClickMic
            )
        },
        containerColor = Color.Transparent,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
        ) {
            conversation.forEach { item ->
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    message = item
                )
            }

            if(isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp)
                )
            }


        }
    }
}

@Composable
fun CustomTopBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        // TODO Not implemented
    }
}

@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier,
    value: String = "",
    isListening: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onClickMic: () -> Unit = {},
    onSendInfo: () -> Unit = {}
) {
    val icon = if (isListening) {
        R.drawable.ic_stop
    } else {
        R.drawable.ic_mic
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(!isListening) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .animateEnterExit(
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally()
                    ),
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = { onSendInfo() }
                )
            )
        }

        IconButton(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
            onClick = onClickMic,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(icon),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    message: MessageItem
) {

    val padding = if (message.isUser) {
        PaddingValues(
            start = 64.dp,
            end = 0.dp,
            top = 0.dp,
            bottom = 0.dp
        )
    } else {
        PaddingValues(
            start = 0.dp,
            end = 64.dp,
            top = 0.dp,
            bottom = 0.dp
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(padding)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = message.message,
        )
    }
}