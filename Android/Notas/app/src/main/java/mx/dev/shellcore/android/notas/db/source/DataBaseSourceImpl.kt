package mx.dev.shellcore.android.notas.db.source

import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.repository.source.DataBaseSource
import javax.inject.Inject

class DataBaseSourceImpl @Inject constructor() : DataBaseSource {

    override suspend fun getNotes(): List<Note> {
        TODO("Not yet implemented")
    }
}