package agency.five.tmdb

import agency.five.tmdb.data.MovieModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

@Composable
fun Movie(
    movie: MovieModel,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.clickable {
            val movieId = movie.id
            onClick("movieDetailScreen/$movieId")
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.movie_picture_rounded_corner)),
    ) {
        //Without the builder with placeholder, no images were loaded
        Image(painter = rememberImagePainter(data = movie.imageUrl, builder = {
            placeholder(R.drawable.iron_man)
        }), contentDescription = null)
        Column() {
            FavoriteButton(movie)
        }
    }
}

@Composable
fun FavoriteButton(
    movie: MovieModel,
) {
    var isFavorite by remember {
        mutableStateOf(movie.isFavorite)
    }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            movie.isFavorite = !movie.isFavorite
        })
    {
        Icon(
            painter = if (movie.isFavorite) {
                painterResource(id = R.drawable.heart2)
            } else {
                painterResource(id = R.drawable.heart)
            }, contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MoviePreview() {
    Movie(PreviewData.getMovies().get(0), { })
}

