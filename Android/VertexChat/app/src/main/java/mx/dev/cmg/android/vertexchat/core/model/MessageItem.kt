package mx.dev.cmg.android.vertexchat.core.model

data class MessageItem(
    val timeStamp: Long,
    val message: String,
    val isUser: Boolean = false
)
