package mx.dev.shellcore.android.notas.repository.impl

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.repository.base.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor() : NoteRepository {

    override suspend fun getAllNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }
}