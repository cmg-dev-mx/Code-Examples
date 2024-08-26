package mx.dev.shellcore.android.handledevents.ui.screen.main.state

data class MainScreenState(
    var isCountButtonVisible: Boolean = false,
    var displayingResult: String = "",
    var inputValue: String = ""
)