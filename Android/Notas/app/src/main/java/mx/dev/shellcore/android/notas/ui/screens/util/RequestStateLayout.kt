package mx.dev.shellcore.android.notas.ui.screens.util

import androidx.compose.runtime.Composable
import mx.dev.shellcore.android.notas.core.model.request.RequestState

@Composable
fun <T> RequestState<T>.DisplayResult(
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    onError: (@Composable () -> Unit)? = null,
    onSuccess: @Composable () -> Unit
) {
    when (this) {
        is RequestState.Idle -> onIdle?.invoke()
        is RequestState.Loading -> onLoading?.invoke()
        is RequestState.Error -> onError?.invoke()
        is RequestState.Success -> onSuccess.invoke()
    }
}