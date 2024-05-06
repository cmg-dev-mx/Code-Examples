package mx.dev.shellcore.android.notes.core.uc.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState

interface GetNoteUseCase {
    suspend fun getNoteById(id: Int): Flow<RequestState<Note>>
}
