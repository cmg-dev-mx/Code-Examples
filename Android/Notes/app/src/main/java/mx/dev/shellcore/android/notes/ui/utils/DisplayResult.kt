package mx.dev.shellcore.android.notes.ui.utils

import androidx.compose.runtime.Composable
import mx.dev.shellcore.android.notes.core.state.RequestState

@Composable
fun <T> RequestState<T>.DisplayResult(
    idle: (@Composable () -> Unit)? = null,
    loading: (@Composable () -> Unit)? = null,
    error: (@Composable (e: String) -> Unit)? = null,
    success: @Composable (data: T) -> Unit
) {
    when (this) {
        is RequestState.Idle -> idle?.invoke()
        is RequestState.Loading -> loading?.invoke()
        is RequestState.Error -> error?.invoke(exception.message ?: "Error")
        is RequestState.Success -> success.invoke(data)
    }
}