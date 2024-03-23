package mx.dev.shellcore.android.notas.ui.route

sealed class BaseRoute(val route: String) {
    data object MainRoute : BaseRoute("main")
}