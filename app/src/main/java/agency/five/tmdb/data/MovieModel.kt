package agency.five.tmdb.data

import agency.five.tmdb.repository.MovieDetailResponse
import agency.five.tmdb.repository.MovieResponse
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class MovieModel(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val categories: List<String>,
    val tags: List<String>,
    var castAndCew: List<CastModel>,
    val score: Float,
    val date: Date?,
    val genres: List<String>,
    val duration: String,
    val overview: String,
    var isFavorite: Boolean
)


const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"

fun MovieResponse.toMovie(isFavorite: Boolean, category: Category, appContext: Context) =
    MovieModel(
        id.toLong(),
        title,
        posterPath?.let { "$BASE_IMAGE_URL/$it" }.toString(),
        listOf(appContext.resources.getString(category.resourceId)),
        listOf(category.tags.random()),
        listOf(),
        popularity,
        try {
            SimpleDateFormat("yyyy-mm-dd").parse(releaseDate)
        } catch (e: ParseException) {
            Date(1, 1, 1)
        },
        // SimpleDateFormat("yyyy-mm-dd").parse(releaseDate),
        genres.map { it.toString() },
        runtime.toString(),
        overview,
        isFavorite
    )

fun MovieDetailResponse.toMovie(isFavorite: Boolean, categoryName: String) = MovieModel(
    id.toLong(),
    title,
    posterPath?.let { "$BASE_IMAGE_URL/$it" }.toString(),
    listOf(),
    listOf(),
    listOf(),
    vote_average * 10,
    Date(
        releaseDate.split("-")[0].toInt(),
        releaseDate.split("-")[1].toInt(),
        releaseDate.split("-")[2].toInt(),
    ),
    genres.map { it.name },
    runtime.toString(),
    overview,
    isFavorite
)

