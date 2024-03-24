package mx.dev.shellcore.android.notas.core.uc.base

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.model.request.RequestState

interface GetNotesUseCase {
    suspend fun getNotes(): Flow<RequestState<List<Note>>>
}
