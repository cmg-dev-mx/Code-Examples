package mx.dev.shellcore.android.dynamiccontent.ui.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.android.dynamiccontent.ui.screen.main.layout.MainLayout

sealed class GraphRoute(val route: String) {
    data object MainGraph : GraphRoute(route = "main")
}

fun NavGraphBuilder.mainGraph() {
    navigation(
        route = GraphRoute.MainGraph.route,
        startDestination = DynamicContentBaseRoute.Main.route
    ) {
        composable(route = DynamicContentBaseRoute.Main.route) { MainLayout() }
    }
}