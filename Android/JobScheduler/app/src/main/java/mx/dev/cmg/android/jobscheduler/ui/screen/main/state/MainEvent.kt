package mx.dev.cmg.android.jobscheduler.ui.screen.main.state

sealed class MainEvent {
    data object OnLoginButtonClick: MainEvent()
}