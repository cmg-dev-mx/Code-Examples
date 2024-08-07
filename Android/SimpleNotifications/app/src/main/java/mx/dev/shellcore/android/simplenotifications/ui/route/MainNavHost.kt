package mx.dev.shellcore.android.simplenotifications.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import kotlinx.serialization.Serializable
import mx.dev.shellcore.android.simplenotifications.ui.screen.main.layout.MainLayout
import mx.dev.shellcore.android.simplenotifications.ui.screen.second.layout.SecondLayout
import mx.dev.shellcore.android.simplenotifications.ui.screen.third.layout.ThirdLayout

@Composable
fun MainNavHost() {

    val navController = rememberNavController()
    val uri = "https://shellcore.mx"

    NavHost(navController = navController, startDestination = MainScreen) {
        composable< MainScreen> {
            MainLayout()
        }
        composable<SecondScreen>(
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/second" })
        ) {
            SecondLayout()
        }
        composable<ThirdScreen>(
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/third" })
        ) {
            ThirdLayout()
        }
    }
}

@Serializable
object MainScreen

@Serializable
object SecondScreen

@Serializable
object ThirdScreen