package mx.dev.shellcore.android.notes.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mx.dev.shellcore.android.notes.ui.screen.detail.layout.DetailLayout
import mx.dev.shellcore.android.notes.ui.screen.list.layout.ListLayout

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ListScreen
    ) {
        composable<ListScreen> {
            ListLayout(navController = navController)
        }
        composable<DetailScreen> {
            val args = it.toRoute<DetailScreen>()
            DetailLayout(navController = navController, id = args.id)
        }
    }
}

@Serializable
object ListScreen

@Serializable
data class DetailScreen(
    val id: Int
)