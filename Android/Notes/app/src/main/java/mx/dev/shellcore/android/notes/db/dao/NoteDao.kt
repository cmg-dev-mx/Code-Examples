package mx.dev.shellcore.android.notes.db.dao

interface NoteDao {
    suspend fun queryAll(): List<mx.dev.shellcore.android.notes.db.model.NoteDO>

}