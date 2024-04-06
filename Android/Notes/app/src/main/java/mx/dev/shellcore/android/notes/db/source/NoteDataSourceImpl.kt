package mx.dev.shellcore.android.notes.db.source

import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.db.dao.NoteDao
import mx.dev.shellcore.android.notes.db.mapper.NoteMapper
import mx.dev.shellcore.android.notes.repostitory.impl.NoteDataSource

class NoteDataSourceImpl(
    private val dao: NoteDao,
    private val mapper: NoteMapper
) : NoteDataSource {

    override suspend fun getList(): List<Note> {
        return dao.queryAll().let { mapper.toModelList(it) }
    }
}