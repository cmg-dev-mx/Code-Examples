package mx.dev.shellcore.android.handledevents.ui.screen.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.handledevents.ui.screen.main.state.MainEvent
import mx.dev.shellcore.android.handledevents.ui.screen.main.state.MainScreenState
import mx.dev.shellcore.android.handledevents.ui.screen.main.state.UIEvent

class MainViewModel : ViewModel() {

    private val _screenState = mutableStateOf(
        MainScreenState(
            isCountButtonVisible = false,
            displayingResult = "Total is 0.0",
            inputValue = ""
        )
    )
    val screenState: State<MainScreenState> = _screenState

    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var total = 0.0

    private fun addToTotal() {
        total += _screenState.value.inputValue.toDouble()
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            inputValue = "",
            isCountButtonVisible = false
        )
    }

    private fun resetTotal() {
        total = 0.0
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            inputValue = "",
            isCountButtonVisible = false
        )
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnInputValueChanged -> {
                _screenState.value = _screenState.value.copy(
                    inputValue = event.value,
                    isCountButtonVisible = event.value.isNotEmpty()
                )
            }

            MainEvent.OnCountButtonClicked -> {
                addToTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(UIEvent.ShowMessage("Value added successfully"))
                }
            }

            MainEvent.OnResetButtonClicked -> {
                resetTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(UIEvent.ShowMessage("Value reset successfully"))
                }
            }
        }
    }
}