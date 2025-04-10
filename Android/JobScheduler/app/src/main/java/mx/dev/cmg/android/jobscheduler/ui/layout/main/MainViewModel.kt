package mx.dev.cmg.android.jobscheduler.ui.layout.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    var isLoadingState = mutableStateOf(false)
        private set

    var loginState = mutableStateOf(false)
        private set

    fun login() {
        viewModelScope.launch {
            isLoadingState.value = true
            delay(2000)
            loginState.value = true
            isLoadingState.value = false
        }
    }
}