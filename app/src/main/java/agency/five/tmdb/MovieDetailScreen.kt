package agency.five.tmdb

import agency.five.tmdb.ui.theme.GreenCircular
import agency.five.tmdb.ui.theme.GreenCircular2
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailScreen(movieId: Long, movies: List<MovieModel>) {

    //Film prema ID-u iz navifacije
    val movieModel: MovieModel = movies.findLast { movie -> movie.id == movieId.toLong() }!!

    LazyColumn(modifier = Modifier.background(Color.White)) {
        item {

            //Osnovni podaci o filmu
            Box(
                modifier = Modifier
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = movieModel.imageId),
                    "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                        .align(
                            Alignment.BottomStart
                        )
                ) {

                    Row() {
                        Box() {
                            CircularProgressBar(
                                modifier = Modifier.size(56.dp),
                                progress = movieModel.score,
                                progressMax = 100f,
                                progressBarColor = GreenCircular,
                                progressBarWidth = 4.dp,
                                backgroundProgressBarColor = GreenCircular2,
                                backgroundProgressBarWidth = 4.dp,
                                roundBorder = true,
                            )
                            Text(
                                movieModel.score.toInt().toString() + "%",
                                modifier = Modifier
                                    .align(Alignment.Center),
                                color = Color.White,
                                style = MaterialTheme.typography.h4
                            )
                        }

                        Text(
                            "User score",
                            color = Color.White,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp)
                        )
                    }

                    Row(modifier = Modifier.padding(vertical = 6.dp)) {
                        Text(
                            movieModel.name,
                            style = MaterialTheme.typography.h5,
                            color = Color.White
                        )
                        Text(
                            " (" + movieModel.date.year.toString() + ")",
                            style = MaterialTheme.typography.h6,
                            color = Color.White
                        )
                    }
                    Row() {
                        Text(
                            movieModel.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " (US)",
                            style = MaterialTheme.typography.h2,
                            color = Color.White
                        )
                    }
                    Row() {
                        Text(
                            movieModel.genres.joinToString(", ") + " ",
                            style = MaterialTheme.typography.h2,
                            color = Color.White
                        )
                        Text(
                            movieModel.duration,
                            style = MaterialTheme.typography.h3,
                            color = Color.White
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.star), "",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

            }
        }

        //Overview
        item() {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Overview",
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    movieModel.overview,
                    style = MaterialTheme.typography.h2,
                    color = Color.Black
                )
            }
        }

        //Writers
        gridItems(
            movieModel.writers, 3, horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) { writer ->

            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row() {
                    Text(
                        writer.nameSurname,
                        style = MaterialTheme.typography.h3,
                        color = Color.Black
                    )
                }
                Row() {
                    Text(
                        writer.role, style = MaterialTheme.typography.h2, color = Color.Black
                    )
                }
            }
        }

        //Cast tekstovi
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Top Billed Cast",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    "Full Cast & Crew",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.End
                )
            }
        }

        //Cast slike i imena
        item() {
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 72.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movieModel.cast.size) { index ->

                    val castMember = movieModel.cast[index]
                    Cast(cast = castMember)
                }
            }
        }


    }

}


@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    progressMax: Float = 100f,
    progressBarColor: Color = Color.Black,
    progressBarWidth: Dp = 7.dp,
    backgroundProgressBarColor: Color = Color.Gray,
    backgroundProgressBarWidth: Dp = 3.dp,
    roundBorder: Boolean = false,
    startAngle: Float = 0f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasSize = size.minDimension
        val radius = canvasSize / 2 - maxOf(backgroundProgressBarWidth, progressBarWidth).toPx() / 2

        drawCircle(
            color = backgroundProgressBarColor,
            radius = radius,
            center = size.center,
            style = Stroke(width = backgroundProgressBarWidth.toPx())
        )

        drawArc(
            color = progressBarColor,
            startAngle = 270f + startAngle,
            sweepAngle = (progress / progressMax) * 360f,
            useCenter = false,
            topLeft = size.center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = progressBarWidth.toPx(),
                cap = if (roundBorder) StrokeCap.Round else StrokeCap.Butt
            )
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(device = Devices.PIXEL)
fun movieDetailPreview() {

    TmdbTheme() {
        MovieDetailScreen(1, PreviewData.getMovies())
    }
}