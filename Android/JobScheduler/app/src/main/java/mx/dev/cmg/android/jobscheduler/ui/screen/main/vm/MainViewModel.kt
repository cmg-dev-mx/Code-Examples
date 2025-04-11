package mx.dev.cmg.android.jobscheduler.ui.screen.main.vm

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainEvent
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainLayoutState
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {

    var layoutState = mutableStateOf(MainLayoutState())
        private set

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLoginButtonClick -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            layoutState.value = MainLayoutState(
                isLoginButtonEnabled = false,
                isLoading = true
            )

            delay(2000)

            layoutState.value = MainLayoutState(
                isLoginButtonEnabled = false,
                isLoading = false,
                isUserLoggedIn = true
            )
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