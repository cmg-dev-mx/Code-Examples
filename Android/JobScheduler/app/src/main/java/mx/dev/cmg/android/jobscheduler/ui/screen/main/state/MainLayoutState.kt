package mx.dev.cmg.android.jobscheduler.ui.screen.main.state

data class MainLayoutState(
    var isLoginButtonEnabled: Boolean = true,
    var isLoading: Boolean = false,
    var isUserLoggedIn: Boolean = false,
)