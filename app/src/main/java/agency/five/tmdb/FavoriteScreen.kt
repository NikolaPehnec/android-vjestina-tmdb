package agency.five.tmdb

import agency.five.tmdb.ui.theme.TmdbTheme
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(movieModelList: List<MovieModel>, navController: NavController?) {
    val favoriteMovies = movieModelList.filter { movie -> movie.isFavorite }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 18.dp)
            .padding(bottom = 56.dp)
    ) {

        Row(modifier = Modifier.padding(top = 32.dp, bottom = 12.dp, start = 4.dp)) {
            Text(
                text = stringResource(id = R.string.title_favorite_screen),
                style = MaterialTheme.typography.h1
            )
        }

        LazyVerticalGrid(cells = GridCells.Fixed(3)) {
            items(favoriteMovies.size) { index ->
                Row(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp),
                ) {
                    val movie = favoriteMovies[index]
                    Movie(movie = movie, navController = navController)
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
        FavoriteScreen(PreviewData.getMovies(),null)
    }
}