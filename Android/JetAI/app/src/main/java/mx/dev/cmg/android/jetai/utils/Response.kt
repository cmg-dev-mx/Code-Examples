package mx.dev.cmg.android.jetai.utils

sealed class Response<out T>(
    val data:T? = null,
    val error: Throwable? = null
) {

    class Loading<T>: Response<T>()
    data class Success<T>(val dataSuccess: T): Response<T>(data = dataSuccess)
    data class Error<T>(val throwable: Throwable?): Response<T>(error = throwable)
}