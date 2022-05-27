package agency.five.tmdb

import agency.five.tmdb.data.Category
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.ui.theme.TmdbTheme
import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieCategory(
    modifier: Modifier = Modifier,
    category: Category,
    movies: List<MovieModel>,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavorite: Boolean) -> Unit
) {
    val context = LocalContext.current

    //Movies filtered by selected tag - Popular/Top rated
    val moviesToPresent = remember { mutableStateListOf<MovieModel>() }

    if (moviesToPresent.size == 0) {
        moviesToPresent.addAll(movies.filter { movie -> movie.tags.contains(category.tags[0]) })
    }


    Column(
        modifier
            .background(color = Color.White)
            .padding(vertical = 18.dp)
    ) {

        Row(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))) {
            Text(
                text = LocalContext.current.resources.getString(category.resourceId),
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
            category.tags.forEachIndexed { index, title ->
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

                        val tagSelected = category.tags[scrollableTabRowState]
                        val moviesByTags =
                            movies.filter { movie -> movie.tags.contains(tagSelected) }
                        moviesToPresent.clear()
                        moviesToPresent.addAll(moviesByTags)
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


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun categoriesPreview() {
    TmdbTheme() {
        MovieCategory(
            category = Category.UPCOMING,
            movies = mutableListOf(),
            onMovieCardClick = {},
            markMovieAsFavorite = { _, _ -> })
    }
}