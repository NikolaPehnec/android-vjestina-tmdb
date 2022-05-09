package agency.five.tmdb

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.ui.theme.Blue
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(
    onMovieCardClick: (String) -> Unit,
) {
    val homeViewModel: HomeViewModel = getViewModel()
    val favoriteViewModel: FavoriteMoviesViewModel=getViewModel()

    val allMovies: List<MovieModel> =
        homeViewModel.getAllMovies().collectAsState(initial = listOf()).value
    val allCategories: List<MovieCategoryModel> =
        homeViewModel.getAllCategories().collectAsState(initial = listOf()).value

    Column(
        Modifier
            .padding(bottom = dimensionResource(id = R.dimen.bottom_padding_big))
    ) {

        SearchField()

        LazyColumn {

            var movies: List<MovieModel>

            items(items = allCategories) { categoryModel ->

                movies = allMovies.filter { movie ->
                    movie.categories.contains(categoryModel.categoryName)
                }

                MovieCategory(
                    categoryModel = categoryModel,
                    movies = movies,
                    onMovieCardClick = onMovieCardClick,
                ) { movie, isFavorite ->
                    favoriteViewModel.markMovieFavourite(movie, isFavorite)
                }
            }

        }
    }
}

@Composable
fun SearchField() {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
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
/*    TmdbTheme() {
        HomeScreen(PreviewData.getCategories(), {})
    }*/
}



