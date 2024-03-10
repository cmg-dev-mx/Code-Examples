package mx.dev.shellcore.android.meals.ui.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.android.meals.ui.screen.mealcategories.layout.MealCategoriesLayout

sealed class MealsGraph(val route: String) {
    data object MealsCategoriesGraph : MealsGraph("main")
}

fun NavGraphBuilder.mainGraph(navController: NavController? = null) {
    navigation(
        route = MealsGraph.MealsCategoriesGraph.route,
        startDestination = MealsBaseRoute.MealsCategoriesRoute.route
    ) {
        composable(MealsBaseRoute.MealsCategoriesRoute.route) { MealCategoriesLayout(navController = navController) }
    }
}
