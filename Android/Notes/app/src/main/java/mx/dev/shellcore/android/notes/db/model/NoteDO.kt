package mx.dev.shellcore.android.notes.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDO(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String?,
    val content: String?,
    val date: Long
)