package mx.dev.shellcore.android.dynamiccontent.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mx.dev.shellcore.android.dynamiccontent.ui.screen.main.layout.MainLayout

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ) {
        composable<MainScreen> { MainLayout() }
    }
}

@Serializable
object MainScreen