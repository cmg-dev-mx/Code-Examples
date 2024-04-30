package mx.dev.shellcore.android.notes.core.uc.impl

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.repository.base.NoteRepository
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase
import javax.inject.Inject

class SaveNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : SaveNoteUseCase {

    override suspend fun saveNote(note: Note): Flow<RequestState<Boolean>> {
        return repository.saveNote(note)
    }
}