package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.DeleteNoteUseCase
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor() : DeleteNoteUseCase {

    override suspend fun deleteNoteById(id: Int): Flow<RequestState<Boolean>> {
        TODO("Not yet implemented")
    }
}