package mx.dev.shellcore.android.handledevents.ui.screen.main.state

sealed class MainEvent {
    data class OnInputValueChanged(val value: String): MainEvent()
    data object OnCountButtonClicked: MainEvent()
    data object OnResetButtonClicked: MainEvent()
}