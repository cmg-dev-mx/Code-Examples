package mx.dev.shellcore.android.notes.repostitory.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState

class NoteRepositoryImpl(
    private val source: NoteDataSource
) : NoteRepository {

    override suspend fun getList(): Flow<RequestState<List<Note>>> {
        return try {
            val response = source.getList()
            flow { emit(RequestState.Success(response)) }
        } catch (e: Exception) {
            flow { emit(RequestState.Error(e)) }
        }
    }
}