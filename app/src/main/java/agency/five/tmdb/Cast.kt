package agency.five.tmdb

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.ui.theme.Gray3
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.foundation.Image
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
fun Cast(
    cast: CastModel
) {
    Card(
        modifier = Modifier
            .sizeIn(maxWidth = dimensionResource(id = R.dimen.cast_picture_max_width))
            .height(
                dimensionResource(id = R.dimen.cast_card_height)
            ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.cast_picture_rounded_corner)),
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = cast.imageUrl, builder = {
                    R.drawable.robert_downey
                }),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.cast_picture_max_width))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Text(
                cast.nameSurname,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.cast_info_horizontal_padding))
                    .padding(top = dimensionResource(id = R.dimen.cast_info_top_padding)),
                style = MaterialTheme.typography.h3,
            )

            Text(
                cast.roleName,
                modifier = Modifier.padding(
                    horizontal =
                    dimensionResource(id = R.dimen.cast_info_horizontal_padding)
                ),
                style = MaterialTheme.typography.subtitle1,
                color = Gray3
            )
        }
    }
}


@Composable
@Preview
fun CastPreview() {
    TmdbTheme {
        Cast(
            cast = CastModel(
                "Robert Downey Jr.",
                "Tony Stark/Iron man",
                R.drawable.robert_downey
            )
        )
    }
}


