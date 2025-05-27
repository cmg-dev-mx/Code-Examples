package mx.dev.cmg.android.geminiai.model

data class MessageItem(
    val id: Long,
    val message: String,
    val isUser: Boolean = false,
)
