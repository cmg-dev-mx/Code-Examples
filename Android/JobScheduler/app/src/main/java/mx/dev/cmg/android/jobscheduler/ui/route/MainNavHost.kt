package mx.dev.cmg.android.jobscheduler.ui.route

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mx.dev.cmg.android.jobscheduler.ui.screen.main.layout.MainLayout

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Main) {
        composable<Main> { MainLayout() }
    }
}

@Serializable
object Main