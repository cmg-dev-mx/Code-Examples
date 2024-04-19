package mx.dev.shellcore.android.notes.ui.route

sealed class NotesBaseRoute(val route: String) {
    data object ListRoute: NotesBaseRoute("list")
    data object DetailRoute: NotesBaseRoute("detail")
}