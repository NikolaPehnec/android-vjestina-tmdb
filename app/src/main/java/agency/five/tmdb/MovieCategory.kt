package agency.five.tmdb

import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MovieCategory(
    modifier: Modifier = Modifier,
    categoryModel: MovieCategoryModel,
    navController: NavController?
) {

    //Filmovi filtrirani po selektiranom tagu - Popular/Top rated
    var moviesToPresent by remember {
        mutableStateOf(categoryModel.movies)
    }

    Column(
        modifier
            .background(color = Color.White)
            .padding(vertical = 18.dp)
    ) {

        Row() {
            Text(
                text = categoryModel.categoryName,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary
            )
        }

        var state by remember {
            mutableStateOf(0)
        }

        ScrollableTabRow(
            selectedTabIndex = state,
            modifier.padding(bottom = 16.dp),
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(it[state]),
                    color = MaterialTheme.colors.primary,
                    height = 4.dp
                )
            },
            divider = {},
            backgroundColor = Color.White,
            edgePadding = 0.dp
        ) {
            categoryModel.tags.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (state == index)
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.secondary,
                            style = if (state == index) MaterialTheme.typography.body2
                            else MaterialTheme.typography.body1
                        )
                    },
                    selected = state == index,
                    onClick = {
                        state = index

                        val tagSelected = categoryModel.tags[state]
                        val moviesByTags =
                            categoryModel.movies.filter { movie -> movie.tags.contains(tagSelected) }
                        moviesToPresent = moviesByTags
                    }
                )
            }

        }


        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(moviesToPresent.size) { index ->

                val movie = moviesToPresent[index]
                Movie(movie = movie, navController = navController)
            }
        }
    }
}


data class MovieCategoryModel(
    val categoryId: Long,
    val categoryName: String,
    val tags: List<String>,
    var movies: List<MovieModel>
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun categoriesPreview() {
    TmdbTheme() {
        MovieCategory(categoryModel = PreviewData.getCategories()[0], navController = null)
    }
}