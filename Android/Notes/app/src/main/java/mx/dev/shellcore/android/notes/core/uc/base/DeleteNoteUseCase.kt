package mx.dev.shellcore.android.notes.core.uc.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.state.RequestState

interface DeleteNoteUseCase {
    suspend fun deleteNoteById(id: Int): Flow<RequestState<Boolean>>
}
