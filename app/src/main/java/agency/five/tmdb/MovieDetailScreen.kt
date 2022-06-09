package agency.five.tmdb

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.ui.theme.GreenCircular
import agency.five.tmdb.ui.theme.GreenCircular2
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.viewModel.MovieDetailViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieDetailScreen(movieId: Long) {
    val movieDetailViewModel: MovieDetailViewModel = getViewModel(
        parameters = { parametersOf(movieId) }
    )

    val movieModel = movieDetailViewModel.getMovieByID().collectAsState(initial = null).value

    val castAndCrew =
        movieDetailViewModel.getMovieCredits().collectAsState(initial = listOf()).value

    val verticalScrollState: ScrollState = rememberScrollState();

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(state = verticalScrollState, enabled = true)
    ) {

        BasicMovieInfo(
            movieModel = movieModel
        )

        MovieOverview(
            movieModel = movieModel
        )

        MovieWriters(crew = movieDetailViewModel.getCrewFromMovieCredits(castAndCrew).take(6))

        MovieCast(cast = movieDetailViewModel.getCastFromMovieCredits(castAndCrew))

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BasicMovieInfo(movieModel: MovieModel?) {
    Box(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.movie_info_height))
    ) {
        if (movieModel != null) {
            Image(
                painter = rememberAsyncImagePainter(movieModel.imageUrl),
                "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.movie_info_height)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(
                    dimensionResource(id = R.dimen.movie_info_padding),
                    dimensionResource(id = R.dimen.movie_info_padding)
                )
                .align(
                    Alignment.BottomStart
                )
        ) {

            Row() {
                Box() {
                    if (movieModel != null) {
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
                if (movieModel != null) {
                    Text(
                        movieModel.name,
                        style = MaterialTheme.typography.h5,
                        color = Color.White
                    )

                    Text(
                        " (" + movieModel.date!!.year.toString() + ")",
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
            }
            if (movieModel != null) {
                Row() {
                    //SimpleDateFormat (dd/MM/yyyy) combined with Date class returns month+1, year+1900
                    //Waiting for date from api to fix this
                    val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/", Locale.US)
                    Text(
                        dateFormat.format(movieModel.date) + movieModel.date!!.year + " (US)",
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
}


@Composable
fun MovieOverview(movieModel: MovieModel?) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
            .padding(top = 24.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            stringResource(id = R.string.movie_overview),
            modifier = Modifier
                .padding(horizontal = 2.dp),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )

        if (movieModel != null) {
            Text(
                movieModel.overview,
                style = MaterialTheme.typography.h2,
                color = Color.Black
            )
        }
    }
}

@Composable
fun MovieWriters(crew: List<CastModel>) {
    val itemWidth: Dp = LocalConfiguration.current.screenWidthDp.dp / 3

    FlowRow(
        mainAxisSize = SizeMode.Expand, mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
    ) {
        crew.forEach { crewMember ->
            Column(
                modifier = Modifier
                    .width(width = itemWidth)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.horizontal_padding),
                        vertical = 18.dp
                    )
            ) {
                Row() {
                    Text(
                        crewMember.nameSurname,
                        style = MaterialTheme.typography.h3,
                        color = Color.Black
                    )
                }
                Row() {
                    Text(
                        crewMember.department!!,
                        style = MaterialTheme.typography.h2,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun MovieCast(cast: List<CastModel>) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
            .padding(top = 18.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Top Billed Cast",
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.horizontal_small_indent))
                .weight(1f),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )

        Text(
            "Full Cast & Crew",
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.horizontal_small_indent))
                .weight(1f),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.End
        )
    }

    LazyRow(
        modifier = Modifier
            .padding(bottom = 72.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.horizontal_padding))
    ) {
        items(cast.size) { index ->

            val castMember = cast[index]
            Cast(cast = castMember)
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
        MovieDetailScreen(1)
    }
}