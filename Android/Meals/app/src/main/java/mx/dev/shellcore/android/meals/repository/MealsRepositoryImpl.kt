package mx.dev.shellcore.android.meals.repository

import mx.dev.shellcore.android.meals.core.model.MealCategory
import mx.dev.shellcore.android.meals.core.repository.MealsRepository
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor() : MealsRepository {

    override suspend fun getMealCategories(): List<MealCategory> {
        return listOf(
            MealCategory(1, "Beef", "https://www.themealdb.com/images/category/beef.png"),
            MealCategory(2, "Chicken", "https://www.themealdb.com/images/category/chicken.png"),
            MealCategory(3, "Dessert", "https://www.themealdb.com/images/category/dessert.png"),
            MealCategory(4, "Lamb", "https://www.themealdb.com/images/category/lamb.png"),
            MealCategory(
                5,
                "Miscellaneous",
                "https://www.themealdb.com/images/category/miscellaneous.png"
            ),
            MealCategory(6, "Pasta", "https://www.themealdb.com/images/category/pasta.png"),
            MealCategory(7, "Pork", "https://www.themealdb.com/images/category/pork.png"),
            MealCategory(8, "Seafood", "https://www.themealdb.com/images/category/seafood.png"),
            MealCategory(9, "Side", "https://www.themealdb.com/images/category/side.png"),
            MealCategory(
                10,
                "Starter",
                "https://www.themealdb.com/images/category/starter.png"
            ),
        )
    }
}