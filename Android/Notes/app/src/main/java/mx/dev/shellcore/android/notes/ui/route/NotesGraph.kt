package mx.dev.shellcore.android.notes.ui.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.android.notes.ui.screen.detail.layout.DetailLayout
import mx.dev.shellcore.android.notes.ui.screen.list.layout.ListLayout

sealed class NotesGraph(val route: String) {
    data object NotesMainGraph: NotesGraph("main")
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        route = NotesGraph.NotesMainGraph.route,
        startDestination = NotesBaseRoute.ListRoute.route
    ) {
        composable(NotesBaseRoute.ListRoute.route) { ListLayout(navController = navController) }
        composable(NotesBaseRoute.DetailRoute.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
            DetailLayout(navController = navController, id = id)
        }
    }
}