package mx.dev.shellcore.android.meals.web.model


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category> = listOf()
)