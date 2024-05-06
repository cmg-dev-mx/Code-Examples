package mx.dev.shellcore.android.notes.core.repository.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState

interface NoteRepository {
    suspend fun getList(): Flow<RequestState<List<Note>>>
    suspend fun saveNote(note: Note): Flow<RequestState<Boolean>>
    suspend fun getNoteById(id: Int): Flow<RequestState<Note>>
}