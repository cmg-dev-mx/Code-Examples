package mx.dev.shellcore.android.notes.core.uc

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState

interface GetNoteListUseCase {
    suspend fun getList(): Flow<RequestState<List<Note>>>
}
