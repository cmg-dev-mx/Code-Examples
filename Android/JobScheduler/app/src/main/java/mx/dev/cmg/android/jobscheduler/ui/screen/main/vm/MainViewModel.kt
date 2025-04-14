package mx.dev.cmg.android.jobscheduler.ui.screen.main.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.core.usecase.welcome.WelcomeNotificationUseCase
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainEvent
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainLayoutState
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.NotificationState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: WelcomeNotificationUseCase,
): ViewModel() {

    var layoutState = mutableStateOf(MainLayoutState())
        private set

    var notificationState = mutableStateOf(NotificationState())
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

    fun validateWelcomeNotification() {
        viewModelScope.launch {
            useCase.validateWelcomeNotification().collect { alreadyShown ->
                if (alreadyShown) return@collect

                notificationState.value = NotificationState(
                    showWelcomeNotification = true
                )
            }
        }
    }
}