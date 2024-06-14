package mx.dev.shellcore.android.profilecard.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import mx.dev.shellcore.android.profilecard.ui.screen.user.layout.UserDetailLayout
import mx.dev.shellcore.android.profilecard.ui.screen.users.layout.UsersLayout

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UsersScreen
    ) {
        composable<UsersScreen> { UsersLayout(navController = navController) }
        composable<UserDetailScreen> {
            val args = it.toRoute<UserDetailScreen>()
            UserDetailLayout(userId = args.id, navController = navController)
        }
    }
}

@Serializable
object UsersScreen

@Serializable
data class UserDetailScreen(
    val id: Int
)