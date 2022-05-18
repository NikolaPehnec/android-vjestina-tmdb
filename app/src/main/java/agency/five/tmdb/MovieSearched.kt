package agency.five.tmdb

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.PreviewData
import agency.five.tmdb.ui.theme.Gray3
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun MovieSearched(
    movie: MovieModel,
    onMovieCardClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .sizeIn(maxHeight = dimensionResource(id = R.dimen.cast_picture_max_width))
            .clickable { onMovieCardClick(movie.id.toString()) },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.cast_picture_rounded_corner)),
        elevation = 5.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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

                Text(
                    movie.name,
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.cast_info_horizontal_padding)),
                    style = MaterialTheme.typography.h3,
                )

                Text(
                    movie.overview,
                    modifier = Modifier.padding(
                        vertical =
                        dimensionResource(id = R.dimen.cast_info_horizontal_padding)
                    ),
                    style = MaterialTheme.typography.subtitle1,
                    color = Gray3
                )
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MovieSearchPreview() {
    MovieSearched(PreviewData.getMovies().get(0), { })
}

