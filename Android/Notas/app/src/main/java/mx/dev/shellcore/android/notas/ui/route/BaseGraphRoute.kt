package mx.dev.shellcore.android.notas.ui.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.android.notas.ui.screens.list.layout.ListLayout

sealed class BaseGraphRoute(val route: String) {
    data object MainGraphRoute : BaseGraphRoute("main_graph")
}

fun NavGraphBuilder.mainGraph() {
    navigation(
        startDestination = BaseRoute.MainRoute.route,
        route = BaseGraphRoute.MainGraphRoute.route
    ) {
        composable(BaseRoute.MainRoute.route) {
            ListLayout()
        }
    }
}