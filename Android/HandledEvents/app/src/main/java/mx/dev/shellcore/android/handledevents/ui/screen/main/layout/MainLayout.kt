package mx.dev.shellcore.android.handledevents.ui.screen.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import mx.dev.shellcore.android.handledevents.ui.screen.main.state.MainEvent
import mx.dev.shellcore.android.handledevents.ui.screen.main.state.UIEvent
import mx.dev.shellcore.android.handledevents.ui.screen.main.viewmodel.MainViewModel
import mx.dev.shellcore.android.handledevents.ui.theme.HandledEventsTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    HandledEventsTheme {
        MainLayout()
    }
}

@Composable
fun MainLayout(
    viewModel: MainViewModel = viewModel()
) {
    val screenState = viewModel.screenState.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { parentPadding ->

        Column(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.displayMedium,
                text = screenState.displayingResult
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onSend = {
                        viewModel.onEvent(MainEvent.OnCountButtonClicked)
                    }
                ),
                value = screenState.inputValue,
                onValueChange = { viewModel.onEvent(MainEvent.OnInputValueChanged(it)) },
                label = { Text("Enter a number") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (screenState.isCountButtonVisible) {
                    Button(
                        onClick = {
                            viewModel.onEvent(MainEvent.OnCountButtonClicked)
                        }
                    ) {
                        Text("Add")
                    }
                }

                Button(
                    onClick = {
                        viewModel.onEvent(MainEvent.OnResetButtonClicked)
                    }
                ) {
                    Text("Reset")
                }
            }
        }
    }
}
