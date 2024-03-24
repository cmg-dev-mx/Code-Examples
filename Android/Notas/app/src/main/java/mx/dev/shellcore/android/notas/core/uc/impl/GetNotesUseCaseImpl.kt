package mx.dev.shellcore.android.notas.core.uc.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.model.request.RequestState
import mx.dev.shellcore.android.notas.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notas.core.uc.base.GetNotesUseCase
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : GetNotesUseCase {

    override suspend fun getNotes(): Flow<RequestState<List<Note>>> {
        return repository.getAllNotes()
            .catch { exception ->
                RequestState.Error(exception)
            }.map { list ->
                RequestState.Success(list)
            }
    }
}