package mx.dev.shellcore.android.notas.ui.screens.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.notas.core.model.Note
import mx.dev.shellcore.android.notas.core.model.request.RequestState
import mx.dev.shellcore.android.notas.core.uc.base.GetNotesUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<RequestState<List<Note>>>(RequestState.Idle)
    val notes = _notes.asStateFlow()

    init {
        viewModelScope.launch {
            _notes.value = RequestState.Loading
            getNotesUseCase.getNotes().collect {
                _notes.value = it
            }
        }
    }
}