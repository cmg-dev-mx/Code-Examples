package mx.dev.shellcore.android.notes.ui.route

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NotesBaseRoute(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object ListRoute : NotesBaseRoute(route = "list")
    data object DetailRoute : NotesBaseRoute(
        route = "detail/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )
}