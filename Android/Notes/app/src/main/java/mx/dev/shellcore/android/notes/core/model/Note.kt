package mx.dev.shellcore.android.notes.core.model

data class Note(
    val id: Int = 0,
    var title: String = "",
    var content: String = "",
    var date: Long = 0L
)
