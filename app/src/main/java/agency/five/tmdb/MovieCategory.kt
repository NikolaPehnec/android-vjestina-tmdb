package agency.five.tmdb

import agency.five.tmdb.data.Category
import agency.five.tmdb.data.MovieModel
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
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieCategory(
    modifier: Modifier = Modifier,
    category: Category,
    moviesFlow: Flow<List<MovieModel>>,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavorite: Boolean) -> Unit
) {
    val movies = moviesFlow.collectAsState(initial = listOf()).value
    val allMovies = remember { mutableStateListOf<MovieModel>() }

    //New movie from flow
    for (newMovie in movies) {
        if (!allMovies.any { it.id == newMovie.id })
            allMovies.add(newMovie)
    }

    //Index of selected tag
    var scrollableTabRowState by remember {
        mutableStateOf(0)
    }

    //Movies filtered by selected tag - Popular/Top rated
    var moviesToPresent by remember { mutableStateOf(emptyList<MovieModel>()) }
    moviesToPresent = allMovies.filter { movie ->
        movie.tags.contains(
            category.tags[scrollableTabRowState]
        )
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
    /*   TmdbTheme() {
           MovieCategory(
               category = Category.UPCOMING,
               moviesFlow = mutableListOf(),
               onMovieCardClick = {},
               markMovieAsFavorite = { _, _ -> })
       }*/
}