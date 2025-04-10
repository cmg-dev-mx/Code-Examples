package mx.dev.cmg.android.jobscheduler.ui.layout.main

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {

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

    fun showWelcomeNotification(title: String, content: String) {
        viewModelScope.launch {
            context.createNotification(
                title = title,
                 content = content
            )
        }
    }
}