package agency.five.tmdb

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.viewModel.MovieSearchViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieSearchScreen(
    query: String,
    onMovieCardClick: (String) -> Unit
) {
    val movieSearchViewModel: MovieSearchViewModel = getViewModel(
        parameters = { parametersOf(query) }
    )

    val movies = movieSearchViewModel.getMoviesQueried().collectAsState(initial = listOf()).value

    MovieSearchContent(
        movies,
        onMovieCardClick = onMovieCardClick
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieSearchContent(
    movies: List<MovieModel>,
    onMovieCardClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
            .padding(bottom = dimensionResource(id = R.dimen.bottom_padding_big))
    ) {

        Row(modifier = Modifier.padding(top = 32.dp, bottom = 12.dp)) {
            Text(
                text = stringResource(id = R.string.title_search_screen),
                style = MaterialTheme.typography.h1
            )
        }

        LazyColumn() {
            items(movies.size) { index ->
                Row(
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                    ),
                ) {
                    val movie = movies[index]
                    MovieSearched(
                        movie = movie,
                        onMovieCardClick = onMovieCardClick,
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MovieSearchScreenPreview() {
    TmdbTheme() {
        MovieSearchContent(PreviewData.getMovies(), {})
    }
}