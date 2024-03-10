package mx.dev.shellcore.android.meals.repository

import mx.dev.shellcore.android.meals.core.model.MealCategory
import mx.dev.shellcore.android.meals.core.repository.MealsRepository
import mx.dev.shellcore.android.meals.web.service.MealsService
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val service: MealsService
) : MealsRepository {

    override suspend fun getMealCategories(): List<MealCategory> {
        return service.getMealCategories().categories.map {
            MealCategory(
                id = it.idCategory.toInt(),
                name = it.strCategory,
                thumbnail = it.strCategoryThumb
            )
        }
    }
}