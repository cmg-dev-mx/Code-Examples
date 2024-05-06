package mx.dev.shellcore.android.notes.repostitory.impl

import mx.dev.shellcore.android.notes.core.model.Note

interface NoteDataSource {
    suspend fun getList(): List<Note>
    suspend fun save(note: Note): Boolean
    suspend fun getNoteById(id: Int): Note
}
