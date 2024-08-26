package mx.dev.shellcore.android.meals.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mx.dev.shellcore.android.meals.ui.screen.mealcategories.layout.MealCategoriesLayout

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MealsScreen
    ) {
        composable<MealsScreen> { MealCategoriesLayout() }
    }
}

@Serializable
object MealsScreen