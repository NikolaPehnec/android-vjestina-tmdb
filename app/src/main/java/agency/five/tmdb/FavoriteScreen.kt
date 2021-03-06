package agency.five.tmdb

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.viewModel.FavoriteMoviesViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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

@Composable
fun FavoriteScreen(
    onMovieCardClick: (String) -> Unit
) {
    val favoriteViewModel: FavoriteMoviesViewModel = getViewModel()
    val favoriteMovies: List<MovieModel> = favoriteViewModel.getFavoriteMovies()
        .collectAsState(initial = listOf()).value

    FavoriteContent(favoriteMovies = favoriteMovies,
        onMovieCardClick = onMovieCardClick,
        markMovieAsFavorite = { movie, isFavorite ->
            {
                favoriteViewModel.markMovieFavourite(movie, isFavorite)
            }
        })

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    favoriteMovies: List<MovieModel>,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavorite: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
            .padding(bottom = dimensionResource(id = R.dimen.bottom_padding_big))
    ) {

        Row(modifier = Modifier.padding(top = 32.dp, bottom = 12.dp)) {
            Text(
                text = stringResource(id = R.string.title_favorite_screen),
                style = MaterialTheme.typography.h1
            )
        }

        LazyVerticalGrid(cells = GridCells.Fixed(3)) {
            items(favoriteMovies.size) { index ->
                Row(
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = dimensionResource(id = R.dimen.horizontal_indent)
                    ),
                ) {
                    val movie = favoriteMovies[index]
                    Movie(
                        movie = movie,
                        onMovieCardClick = onMovieCardClick,
                        markMovieAsFavorite = markMovieAsFavorite
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FavoriteScreenPreview() {
    TmdbTheme() {
        FavoriteContent(PreviewData.getMovies(), {}, { _, _ -> {} })
    }
}