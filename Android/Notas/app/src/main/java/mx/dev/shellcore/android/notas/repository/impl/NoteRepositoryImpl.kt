package mx.dev.shellcore.android.notas.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notas.repository.source.DataBaseSource
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dbSource: DataBaseSource
) : NoteRepository {

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return flow {
            emit(dbSource.getNotes())
        }
    }
}