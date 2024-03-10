package mx.dev.shellcore.android.meals.core.repository

import mx.dev.shellcore.android.meals.core.model.MealCategory

interface MealsRepository {
    suspend fun getMealCategories(): List<MealCategory>
}