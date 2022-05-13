package agency.five.tmdb

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieCategory(
    modifier: Modifier = Modifier,
    categoryModel: MovieCategoryModel,
    movies: List<MovieModel>,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavorite: Boolean) -> Unit
) {

    //Movies filtered by selected tag - Popular/Top rated
    var moviesToPresent by remember {
        mutableStateOf(movies)
    }

    Column(
        modifier
            .background(color = Color.White)
            .padding(vertical = 18.dp)
    ) {

        Row(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))) {
            Text(
                text = categoryModel.categoryName,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary
            )
        }

        var scrollableTabRowState by remember {
            mutableStateOf(0)
        }

        ScrollableTabRow(
            selectedTabIndex = scrollableTabRowState,
            modifier.padding(bottom = 16.dp),
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(it[scrollableTabRowState]),
                    color = MaterialTheme.colors.primary,
                    height = 4.dp
                )
            },
            divider = {},
            backgroundColor = Color.White,
            edgePadding = dimensionResource(id = R.dimen.horizontal_padding)
        ) {
            categoryModel.tags.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (scrollableTabRowState == index)
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.secondary,
                            style = if (scrollableTabRowState == index) MaterialTheme.typography.body2
                            else MaterialTheme.typography.body1
                        )
                    },
                    selected = scrollableTabRowState == index,
                    onClick = {
                        scrollableTabRowState = index

                        val tagSelected = categoryModel.tags[scrollableTabRowState]
                        val moviesByTags =
                            movies.filter { movie -> movie.tags.contains(tagSelected) }
                        moviesToPresent = moviesByTags
                    }
                )
            }

        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.horizontal_padding)),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(moviesToPresent.size) { index ->

                val movie = moviesToPresent[index]
                Movie(
                    movie = movie,
                    onMovieCardClick = onMovieCardClick,
                    markMovieAsFavorite = markMovieAsFavorite
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun categoriesPreview() {
    TmdbTheme() {
        MovieCategory(categoryModel = PreviewData.getCategories()[0],
            movies = PreviewData.getMovies(),
            onMovieCardClick = {},
            markMovieAsFavorite = { _, _ -> })
    }
}