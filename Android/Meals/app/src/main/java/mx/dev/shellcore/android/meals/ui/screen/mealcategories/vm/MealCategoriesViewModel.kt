package mx.dev.shellcore.android.meals.ui.screen.mealcategories.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.meals.core.model.MealCategory
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel @Inject constructor() : ViewModel() {

    private val _mealCategories = MutableStateFlow(emptyList<MealCategory>())
    val mealCategories = _mealCategories.asStateFlow()

    fun getMealCategories() {
        viewModelScope.launch {
            val list = listOf(
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
            _mealCategories.value = list
        }
    }
}