package mx.dev.cmg.android.jobscheduler.ui.screen.main.state

sealed class MainEvent {
    data object OnLoginButtonClick: MainEvent()
    data object OnStopJobButtonClick: MainEvent()
    data object ValidateNotificationWelcome: MainEvent()
    data object ValidateNotificationOneDayLogin: MainEvent()
    data object ValidateLoginSession: MainEvent()
}