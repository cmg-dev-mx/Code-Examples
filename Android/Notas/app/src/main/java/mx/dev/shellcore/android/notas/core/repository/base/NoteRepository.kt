package mx.dev.shellcore.android.notas.core.repository.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notas.core.model.Note

interface NoteRepository {
    suspend fun getAllNotes(): Flow<List<Note>>
}
