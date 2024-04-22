package mx.dev.shellcore.android.notes.ui.screen.detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase

class DetailViewModel(
    private val useCase: SaveNoteUseCase
) : ViewModel() {

    private val _noteState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val noteState = _noteState.asStateFlow()

    fun saveNote(note: Note) {
        viewModelScope.launch {
            useCase.saveNote(note).collect {
                _noteState.value = it
            }
        }
    }
}