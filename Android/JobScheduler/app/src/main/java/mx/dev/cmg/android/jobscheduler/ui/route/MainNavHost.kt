package mx.dev.cmg.android.jobscheduler.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mx.dev.cmg.android.jobscheduler.ui.layout.main.MainLayout

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Main) {
        composable<Main> {
            MainLayout()
        }
    }
}

@Serializable
object Main