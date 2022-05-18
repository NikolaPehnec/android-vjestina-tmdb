package agency.five.tmdb

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import agency.five.tmdb.ui.theme.Blue
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.viewModel.FavoriteMoviesViewModel
import agency.five.tmdb.viewModel.HomeViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onSearchPressed: (String) -> Unit,
    onMovieCardClick: (String) -> Unit,
) {
    val homeViewModel: HomeViewModel = getViewModel()
    val favoriteViewModel: FavoriteMoviesViewModel = getViewModel()

    val categories = mutableListOf<MovieCategoryModel>()
    categories.addAll(homeViewModel.getAllCategories().collectAsState(initial = listOf()).value)

    val movies = remember { mutableStateListOf<MovieModel>() }
    //Adding to collection if it's not already added (was stored in (mock)DB)
    for (movie in homeViewModel.getAllMovies().collectAsState(initial = listOf()).value) {
        if (movies.find { m -> m.id == movie.id } == null) {
            movies.add(movie)
        }
    }

    HomeContent(
        categories,
        movies,
        onMovieCardClick = onMovieCardClick,
        markMovieAsFavorite = { movie, isFavorite ->
            {
                favoriteViewModel.markMovieFavourite(movie, isFavorite)
            }
        },
        onSearchPressed = onSearchPressed
    )
}

@Composable
fun HomeContent(
    categories: List<MovieCategoryModel>,
    movies: MutableList<MovieModel>,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavorite: Boolean) -> Unit,
    onSearchPressed: (String) -> Unit
) {
    Column(
        Modifier
            .padding(bottom = dimensionResource(id = R.dimen.bottom_padding_big))
    ) {

        SearchField(onSearchPressed)


        LazyColumn {

            items(items = categories) { category ->

                MovieCategory(
                    categoryModel = category,
                    movies = (movies.filter { it.categories.contains(category.categoryName) }).toMutableList(),
                    onMovieCardClick = onMovieCardClick,
                    markMovieAsFavorite = markMovieAsFavorite
                )
            }

        }
    }
}

@Composable
fun SearchField(
    onSearchPressed: (String) -> Unit,
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
            .padding(top = 16.dp, bottom = 4.dp),
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        label = {
            Text(
                stringResource(id = R.string.search), style = MaterialTheme.typography.button,
                modifier = Modifier.padding(top = 2.dp)
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onSearchPressed(textState.value.text)
                },
            ) {
                Icon(
                    Icons.Filled.Search, "",
                    tint = Blue
                )
            }
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close, "",
            )
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_text_rounded_corner)),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    TmdbTheme() {
        HomeContent(
            PreviewData.getCategories(),
            mutableListOf(),
            onMovieCardClick = { {} },
            markMovieAsFavorite = { _, _ -> {} },
            {}
        )
    }
}



