package mx.dev.shellcore.android.profilecard.ui.route

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ProfileCardBaseRoute(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Users : ProfileCardBaseRoute(route = "users")
    data object UserDetail : ProfileCardBaseRoute(
        route = "users/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )
}