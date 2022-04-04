package agency.five.tmdb

import android.os.Build
import android.os.Parcelable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Composable
fun Movie(
    movie: MovieModel,
    navController: NavController?
) {
    Card(
        modifier = Modifier.clickable(onClick = {
            val movieId = movie.id
            navController?.navigate("movieDetailScreen/$movieId")
        }),
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(painter = painterResource(id = movie.imageId), contentDescription = null)
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

data class MovieModel(
    val id: Long,
    val name: String,
    val imageId: Int,
    val categories: List<String>,
    val tags: List<String>,
    val writers: List<Writer>,
    val cast: List<CastModel>,
    val score: Float,
    val date: LocalDate,
    val genres: List<String>,
    val duration: String,
    val overview: String,
    var isFavorite: Boolean
)

data class Writer(
    val nameSurname: String,
    val role: String
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MoviePreview() {
    Movie(PreviewData.getMovies().get(0), null)
}

