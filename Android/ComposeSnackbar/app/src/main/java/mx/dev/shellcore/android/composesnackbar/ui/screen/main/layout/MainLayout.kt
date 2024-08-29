package mx.dev.shellcore.android.composesnackbar.ui.screen.main.layout

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.composesnackbar.ui.theme.ComposeSnackbarTheme

@Preview
@Composable
private fun MainLayoutPreview() {
    ComposeSnackbarTheme {
        MainLayout()
    }
}

@Composable
fun MainLayout() {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { parentPadding ->
        Column(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Hello, Snackbar!",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            Log.i("TAG", "MainLayout: Action performed")
                        }

                        SnackbarResult.Dismissed -> {
                            Log.i("TAG", "MainLayout: Dismissed")
                        }
                    }
                }
            }) {
                Text(text = "Show Snackbar")
            }
        }

    }
}
