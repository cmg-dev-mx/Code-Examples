package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteListUseCase
import javax.inject.Inject

class GetNoteListUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : GetNoteListUseCase {

    override suspend fun getList(): Flow<RequestState<List<Note>>> {
        return repository.getList()
    }
}