package mx.dev.shellcore.android.notes.db.mapper

import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.db.model.NoteDO
import javax.inject.Inject

class NoteMapper @Inject constructor() {
    fun toModelList(noteDOList: List<NoteDO>): List<Note> {
        return noteDOList.map { noteDO ->
            Note(
                id = noteDO.id,
                title = noteDO.title ?: "",
                content = noteDO.content ?: "",
                date = noteDO.date
            )
        }
    }

    fun toDataObject(note: Note): NoteDO {
        return NoteDO(
            id = note.id,
            title = note.title,
            content = note.content,
            date = note.date
        )
    }

    fun toModel(noteDO: NoteDO): Note {
        return Note(
            id = noteDO.id,
            title = noteDO.title ?: "",
            content = noteDO.content ?: "",
            date = noteDO.date
        )
    }
}
