package mx.dev.shellcore.android.cache.core.request

sealed class RequestState<out T> {

    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val exception: Throwable) : RequestState<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun getSuccessData() = (this as? Success)?.data
    fun getErrorException() = (this as? Error)?.exception
}
