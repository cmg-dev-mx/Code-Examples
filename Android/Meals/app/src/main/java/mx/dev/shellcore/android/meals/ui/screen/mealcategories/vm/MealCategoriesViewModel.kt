package mx.dev.shellcore.android.meals.ui.screen.mealcategories.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.meals.core.model.MealCategory
import mx.dev.shellcore.android.meals.core.repository.MealsRepository
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel @Inject constructor(
    private val repository: MealsRepository
) : ViewModel() {

    private val _mealCategories = MutableStateFlow(emptyList<MealCategory>())
    val mealCategories = _mealCategories.asStateFlow()

    fun getMealCategories() {
        viewModelScope.launch {
            _mealCategories.value = repository.getMealCategories()
        }
    }
}