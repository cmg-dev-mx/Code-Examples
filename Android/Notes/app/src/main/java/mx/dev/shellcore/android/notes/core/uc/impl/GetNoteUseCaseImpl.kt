package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteUseCase
import javax.inject.Inject

class GetNoteUseCaseImpl @Inject constructor() : GetNoteUseCase {

    override suspend fun getNoteById(id: Int): Flow<RequestState<Note>> {
        TODO("Not yet implemented")
    }
}