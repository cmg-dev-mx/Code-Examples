package mx.dev.shellcore.android.notes.repostitory.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
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

    override suspend fun saveNote(note: Note): Flow<RequestState<Boolean>> {
        return try {
            val response = source.save(note)
            flow { emit(RequestState.Success(response)) }
        } catch (e: Exception) {
            flow { emit(RequestState.Error(e)) }
        }
    }

    override suspend fun getNoteById(id: Int): Flow<RequestState<Note>> {
        return try {
            val response = source.getNoteById(id)
            flow { emit(RequestState.Success(response)) }
        } catch (e: Exception) {
            flow { emit(RequestState.Error(e)) }
        }
    }

    override suspend fun deleteNoteById(id: Int): Flow<RequestState<Boolean>> {
        TODO("Not yet implemented")
    }
}