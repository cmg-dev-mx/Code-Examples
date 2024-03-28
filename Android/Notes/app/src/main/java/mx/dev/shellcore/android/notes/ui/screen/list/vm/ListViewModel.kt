package mx.dev.shellcore.android.notes.ui.screen.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.notes.core.model.Note
import mx.dev.shellcore.android.notes.core.state.RequestState
import mx.dev.shellcore.android.notes.core.uc.base.GetNoteListUseCase

class ListViewModel(useCase: GetNoteListUseCase) : ViewModel() {

    private val _noteState = MutableStateFlow<RequestState<List<Note>>>(RequestState.Idle)
    val noteState = _noteState.asStateFlow()

    init {
        viewModelScope.launch {
            _noteState.value = RequestState.Loading
            useCase.getList().collect {
                _noteState.value = it
            }
        }
    }
}