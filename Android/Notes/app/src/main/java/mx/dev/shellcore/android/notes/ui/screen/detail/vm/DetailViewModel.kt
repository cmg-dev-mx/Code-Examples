package mx.dev.shellcore.android.notes.ui.screen.detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.DeleteNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteUseCase
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _note = MutableStateFlow(Note())
    val note = _note.asStateFlow()

    private val _noteLoadedState = MutableStateFlow<RequestState<Note>>(RequestState.Idle)
    val noteLoadedState = _noteLoadedState.asStateFlow()

    private val _noteSavedState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val noteSavedState = _noteSavedState.asStateFlow()

    fun saveNote(note: Note, timeInMillis: Long) {
        viewModelScope.launch {
            note.date = timeInMillis
            saveNoteUseCase.saveNote(note).collect {
                _noteSavedState.value = it
            }
        }
    }

    fun setNoteTitle(title: String) {
        _note.value = _note.value.copy(title = title)
    }

    fun setNoteContent(content: String) {
        _note.value = _note.value.copy(content = content)
    }

    fun setNoteDate(date: Long) {
        _note.value = _note.value.copy(date = date)
    }

    fun updateNoteSavedState(value: Boolean) {
        _noteSavedState.value = RequestState.Success(value)
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            getNoteUseCase.getNoteById(id).collect {
                _noteLoadedState.value = it
                if (it is RequestState.Success) {
                    _note.value = it.data
                }
            }
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNoteById(id).collect {
                _noteSavedState.value = it
            }
        }
    }
}