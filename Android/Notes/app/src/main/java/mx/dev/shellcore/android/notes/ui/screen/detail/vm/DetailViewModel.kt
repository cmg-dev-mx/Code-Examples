package mx.dev.shellcore.android.notes.ui.screen.detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.SaveNoteUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: SaveNoteUseCase
) : ViewModel() {

    private val _noteSavedState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val noteSavedState = _noteSavedState.asStateFlow()

    fun saveNote(note: Note) {
        viewModelScope.launch {
            useCase.saveNote(note).collect {
                _noteSavedState.value = it
            }
        }
    }
}