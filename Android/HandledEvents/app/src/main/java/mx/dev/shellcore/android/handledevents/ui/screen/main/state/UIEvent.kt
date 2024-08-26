package mx.dev.shellcore.android.handledevents.ui.screen.main.state

sealed class UIEvent {
    data class ShowMessage(val message: String): UIEvent()
}