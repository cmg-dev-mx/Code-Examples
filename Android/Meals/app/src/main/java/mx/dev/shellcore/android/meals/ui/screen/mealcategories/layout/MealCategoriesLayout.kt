package mx.dev.shellcore.android.meals.ui.screen.mealcategories.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import mx.dev.shellcore.android.meals.R
import mx.dev.shellcore.android.meals.core.model.MealCategory

@Composable
fun MealCategoriesLayout(navController: NavController? = null) {

    val mealCategories = listOf(
        MealCategory(
            1,
            "Beef",
            "https://www.themealdb.com/images/category/beef.png"
        ),
    )

    MealCategoriesLayoutContainer(mealCategories) {}
}

@Preview
@Composable
private fun MealCategoriesLayoutContainerPreview() {
    val mealCategories = listOf<MealCategory>(
        MealCategory(
            1,
            "Beef",
            "https://www.themealdb.com/images/category/beef.png"
        ),
    )
    MealCategoriesLayoutContainer(mealCategories = mealCategories) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MealCategoriesLayoutContainer(
    mealCategories: List<MealCategory>,
    onClickMealCategory: (MealCategory) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.mealCategories_title))
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                }
            )
        }
    ) { parentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(mealCategories) { mealCategory ->
                MealCategoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    mealCategory = mealCategory,
                    onClickMealCategory = { onClickMealCategory(mealCategory) }
                )
            }
        }
    }
}

@Composable
private fun MealCategoryItem(
    modifier: Modifier,
    mealCategory: MealCategory,
    onClickMealCategory: (MealCategory) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onClickMealCategory(mealCategory) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp),
                painter = rememberImagePainter(data = mealCategory.thumbnail),
                contentDescription = null
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = mealCategory.name,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}


