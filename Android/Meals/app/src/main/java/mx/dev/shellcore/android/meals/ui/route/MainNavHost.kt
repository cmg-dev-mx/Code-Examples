package mx.dev.shellcore.android.meals.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MealsGraph.MealsCategoriesGraph.route
    ) {
        mainGraph(navController = navController)
    }
}