package mx.dev.shellcore.android.profilecard.ui.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.android.profilecard.ui.screen.users.layout.UsersLayout

sealed class GraphRoute(val route: String) {
    data object MainGraph : GraphRoute("main")
}

fun NavGraphBuilder.mainGraph(navController: NavController? = null) {
    navigation(
        route = GraphRoute.MainGraph.route,
        startDestination = ProfileCardBaseRoute.Users.route
    ) {
        composable(route = ProfileCardBaseRoute.Users.route) { UsersLayout(navController) }
    }
}