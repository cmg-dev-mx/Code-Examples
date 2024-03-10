package mx.dev.shellcore.android.meals.ui.route

sealed class MealsBaseRoute(val route: String) {
    data object MealsCategoriesRoute : MealsBaseRoute("meals-categories")
}