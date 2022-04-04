package agency.five.tmdb

import agency.five.tmdb.ui.theme.Blue
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun HomeScreen(movieCategoryModelList: List<MovieCategoryModel>, navController: NavController?) {

    Column(
        Modifier
            .padding(horizontal = 18.dp)
            .padding(bottom = 56.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp),
            value = "", onValueChange = {},
            label = {
                Text(
                    stringResource(id = R.string.search), style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(top = 2.dp)
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search, stringResource(id = R.string.search_box_desc),
                    tint = Blue
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        LazyColumn {
            items(count = movieCategoryModelList.size) { index ->
                val categoryModel = movieCategoryModelList[index]
                categoryModel.movies =
                    categoryModel.movies.filter { movie ->
                        movie.categories.contains(categoryModel.categoryName)
                    }

                MovieCategory(
                    categoryModel = categoryModel,
                    navController = navController
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    TmdbTheme() {
        HomeScreen(PreviewData.getCategories(), null)
    }
}



