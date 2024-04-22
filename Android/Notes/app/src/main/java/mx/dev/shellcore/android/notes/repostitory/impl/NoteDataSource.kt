package mx.dev.shellcore.android.notes.repostitory.impl

import mx.dev.shellcore.android.notes.core.model.Note

interface NoteDataSource {
    suspend fun getList(): List<Note>
    fun save(note: Note): Boolean
}
