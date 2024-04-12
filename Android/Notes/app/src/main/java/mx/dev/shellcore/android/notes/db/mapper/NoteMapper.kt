package mx.dev.shellcore.android.notes.db.mapper

import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.db.model.NoteDO
import javax.inject.Inject

class NoteMapper @Inject constructor() {
    fun toModelList(daoResponse: List<NoteDO>): List<Note> {
        return daoResponse.map { noteDO ->
            Note(
                id = noteDO.id,
                title = noteDO.title ?: "",
                content = noteDO.content ?: "",
                date = noteDO.date
            )
        }
    }
}
