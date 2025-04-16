package mx.dev.cmg.android.jobscheduler.ui.screen.main.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jobscheduler.core.usecase.login.LoginSessionUseCase
import mx.dev.cmg.android.jobscheduler.core.usecase.login.LoginUseCase
import mx.dev.cmg.android.jobscheduler.core.usecase.onedaylogin.OneDayWithoutLoginUseCase
import mx.dev.cmg.android.jobscheduler.core.usecase.welcome.WelcomeNotificationUseCase
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainEvent
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainLayoutState
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.NotificationState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val welcomeNotificationUseCase: WelcomeNotificationUseCase,
    private val oneDayWithoutLoginUseCase: OneDayWithoutLoginUseCase,
    private val loginUseCase: LoginUseCase,
    private val loginSessionUseCase: LoginSessionUseCase
) : ViewModel() {

    var layoutState = mutableStateOf(MainLayoutState())
        private set

    var notificationState = mutableStateOf(NotificationState())
        private set

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLoginButtonClick -> login()
            is MainEvent.OnStopJobButtonClick -> stopJob()
            MainEvent.ValidateLoginSession -> validateLoginSession()
            MainEvent.ValidateNotificationOneDayLogin -> validateOneDayLoginNotification()
            MainEvent.ValidateNotificationWelcome -> validateWelcomeNotification()
        }
    }

    private fun validateWelcomeNotification() {
        viewModelScope.launch {
            welcomeNotificationUseCase.validateWelcomeNotification().collect { alreadyShown ->
                Log.d("MOGC", "validateWelcomeNotification: $alreadyShown")
                if (alreadyShown) return@collect

                notificationState.value = NotificationState(
                    showWelcomeNotification = true
                )
            }
        }
    }

    private fun validateOneDayLoginNotification() {
        viewModelScope.launch {
            Log.d("MOGC", "validateOneDayLoginNotification")
            oneDayWithoutLoginUseCase.validateOneDayWithoutLoginNotification().collect { loggedIn ->
                return@collect
            }
        }
    }

    private fun validateLoginSession() {
        viewModelScope.launch {
            loginSessionUseCase.isLoggedIn().collect { logged ->
                layoutState.value = MainLayoutState(
                    isUserLoggedIn = logged
                )
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
            loginUseCase.login().collect { logged ->
                if (logged) {
                    layoutState.value = MainLayoutState(
                        isLoginButtonEnabled = false,
                        isLoading = false,
                        isUserLoggedIn = true
                    )
                } else {
                    layoutState.value = MainLayoutState(
                        isLoginButtonEnabled = true,
                        isLoading = false,
                        isUserLoggedIn = false
                    )
                }
            }
        }
    }

    private fun stopJob() {
        viewModelScope.launch {
            oneDayWithoutLoginUseCase.stopJob().collect { stopped ->
                return@collect
            }
        }
    }
}