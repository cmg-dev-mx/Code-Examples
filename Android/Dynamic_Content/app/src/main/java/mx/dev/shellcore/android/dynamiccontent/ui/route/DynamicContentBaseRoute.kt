package mx.dev.shellcore.android.dynamiccontent.ui.route

sealed class DynamicContentBaseRoute(val route: String) {
    data object Main : DynamicContentBaseRoute(route = "main_screen")
}
