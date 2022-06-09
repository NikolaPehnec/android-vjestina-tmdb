package agency.five.tmdb

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

@Composable
fun Movie(
    movie: MovieModel,
    onMovieCardClick: (String) -> Unit,
    markMovieAsFavorite: (movie: MovieModel, isFavourite: Boolean) -> Unit
) {
    Card(
        modifier = Modifier.clickable {
            onMovieCardClick(movie.id.toString())
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.movie_picture_rounded_corner)),
    ) {
        Image(
            painter = rememberImagePainter(data = movie.imageUrl, builder = {
                R.drawable.iron_man
            }),
            modifier = Modifier
                .sizeIn(
                    maxWidth = dimensionResource(id = R.dimen.movie_picture_max_width),
                    maxHeight = dimensionResource(id = R.dimen.movie_picture_max_height)
                )
                .fillMaxHeight(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column() {
            FavoriteButton(movie, markMovieAsFavorite)
        }
    }
}

@Composable
fun FavoriteButton(
    movie: MovieModel,
    markMovieAsFavorite: (movie: MovieModel, isFavourite: Boolean) -> Unit,
) {
    var isFavorite by remember {
        mutableStateOf(movie.isFavorite)
    }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            movie.isFavorite = !movie.isFavorite
            markMovieAsFavorite(movie, it)
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
    Movie(PreviewData.getMovies().get(0), { }, { _, _ -> })
}

