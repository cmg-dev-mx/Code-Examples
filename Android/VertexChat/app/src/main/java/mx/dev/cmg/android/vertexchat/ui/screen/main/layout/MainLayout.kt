package mx.dev.cmg.android.vertexchat.ui.screen.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.vertexchat.R
import mx.dev.cmg.android.vertexchat.core.model.MessageItem
import mx.dev.cmg.android.vertexchat.ui.screen.main.vm.InputSate
import mx.dev.cmg.android.vertexchat.ui.screen.main.vm.MainViewModel
import mx.dev.cmg.android.vertexchat.ui.screen.main.vm.UiEvent
import mx.dev.cmg.android.vertexchat.ui.theme.VertexChatTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    VertexChatTheme {
        MainLayout(
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun MainLayout(modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel = hiltViewModel<MainViewModel>()

    val conversation = viewModel.uiState.conversation
    val text = viewModel.uiState.text
    val listenState = viewModel.uiState.listeningState
    val confirmationState = viewModel.uiState.confirmation

    LaunchedEffect(conversation.size) {
        coroutineScope.launch {
            listState.scrollToItem(conversation.size - 1)
        }
    }

    LaunchedEffect(confirmationState) {
        if (confirmationState.isNotBlank()) {
            snackbarHostState.showSnackbar(
                message = confirmationState,
                duration = SnackbarDuration.Long
            )
        }
    }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.Bottom
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.Bottom
                ),
            ) {
                items(conversation) {
                    ChatBubble(
                        modifier = Modifier.fillMaxWidth(),
                        it
                    )
                }
            }

            ChatInputLayout(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                onTextChange = { viewModel.onEvent(UiEvent.OnTextChange(it)) },
                onTextNext = { viewModel.onEvent(UiEvent.OnSendClick) },
                onListen = { viewModel.onEvent(UiEvent.OnStartListening) },
                onStopListening = { viewModel.onEvent(UiEvent.OnStopListening) },
                inputStatus = listenState
            )
        }
    }
}

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    message: MessageItem
) {
    val paddingValues = if (message.isUser) {
        PaddingValues(start = 48.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
    } else {
        PaddingValues(start = 0.dp, end = 48.dp, top = 0.dp, bottom = 0.dp)
    }

    val textAlign = if (message.isUser) {
        TextAlign.End
    } else {
        TextAlign.Start
    }

    val color = if (message.isUser) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.primary
    }

    val shape = if (message.isUser) {
        RoundedCornerShape(8.dp, 8.dp, 0.dp, 8.dp)
    } else {
        RoundedCornerShape(8.dp, 8.dp, 8.dp, 0.dp)
    }

    Row(
        modifier = modifier
            .padding(paddingValues),
        horizontalArrangement = if (message.isUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        },

        ) {


        Text(
            text = message.message,
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = shape
                )
                .padding(8.dp),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = textAlign,
            color = color
        )
    }
}

@Preview
@Composable
private fun ChatInputPreview() {

    val text = remember { mutableStateOf("") }
    val onTextChange: (String) -> Unit = {
        text.value = it
    }

    val listenState = remember { mutableStateOf(InputSate.IDLE) }

    VertexChatTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            ChatInputLayout(
                modifier = Modifier.fillMaxWidth(),
                text = text.value,
                onTextChange = onTextChange,
                onListen = {
                    listenState.value = InputSate.LISTENING
                },
                onStopListening = {
                    listenState.value = InputSate.IDLE
                },
                inputStatus = listenState.value,
            )
        }
    }
}

@Composable
fun ChatInputLayout(
    modifier: Modifier = Modifier,
    text: String = "",
    onTextChange: (String) -> Unit = {},
    onTextNext: () -> Unit = {},
    onListen: () -> Unit = {},
    onStopListening: () -> Unit = {},
    inputStatus: InputSate = InputSate.IDLE
) {
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorIndicatorColor = Color.Transparent,
            errorTrailingIconColor = MaterialTheme.colorScheme.primary,
            errorContainerColor = Color.Transparent
        ),
        trailingIcon = {
            when (inputStatus) {
                InputSate.IDLE -> Icon(
                    modifier = Modifier.clickable { onListen() },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_mic),
                    contentDescription = "Search"
                )

                InputSate.LISTENING -> Icon(
                    modifier = Modifier.clickable { onStopListening() },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_hearing),
                    contentDescription = "Search"
                )

                InputSate.LOADING -> CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                InputSate.SEND -> Icon(
                    modifier = Modifier.clickable { onTextNext() },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_send),
                    contentDescription = "Search"
                )
            }
        },
        placeholder = {
            Text(
                text = "Type a message",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onTextNext() }
        )
    )
}