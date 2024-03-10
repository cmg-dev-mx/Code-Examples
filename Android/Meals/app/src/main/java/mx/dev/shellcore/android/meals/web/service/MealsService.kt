package mx.dev.shellcore.android.meals.web.service

import mx.dev.shellcore.android.meals.web.model.CategoriesResponse
import retrofit2.http.GET

interface MealsService {

    @GET("categories.php")
    suspend fun getMealCategories(): CategoriesResponse
}