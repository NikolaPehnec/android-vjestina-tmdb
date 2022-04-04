package agency.five.tmdb

import agency.five.tmdb.ui.theme.Gray3
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize

@Composable
fun Cast(
    cast: CastModel
) {
    Card(
        modifier = Modifier
            .sizeIn(maxWidth = 120.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = cast.imageId),
                modifier = Modifier
                    .size(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .height(90.dp)
                    .padding(horizontal = 7.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row() {
                    Text(
                        cast.nameSurname,
                        style = MaterialTheme.typography.h3,
                    )
                }
                Row() {
                    Text(
                        cast.roleName,
                        style = MaterialTheme.typography.subtitle1,
                        color = Gray3
                    )
                }
            }


        }
    }
}

data class CastModel(
    val nameSurname: String,
    val roleName: String,
    val imageId: Int
)

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


