package mx.dev.shellcore.android.notas.repository.source

import mx.dev.shellcore.android.notas.core.model.Note

interface DataBaseSource {
    suspend fun getNotes(): List<Note>
}
