package mx.dev.shellcore.android.notes.core.uc.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState

interface SaveNoteUseCase {
    suspend fun saveNote(note: Note): Flow<RequestState<Boolean>>
}
