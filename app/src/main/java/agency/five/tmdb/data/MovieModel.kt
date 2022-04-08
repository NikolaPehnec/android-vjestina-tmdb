package agency.five.tmdb.data

import java.util.*

data class MovieModel(
    val id: Long,
    val name: String,
    val imageUrl: Int,
    val categories: List<String>,
    val tags: List<String>,
    val writers: List<Writer>,
    val cast: List<CastModel>,
    val score: Float,
    val date: Date,
    val genres: List<String>,
    val duration: String,
    val overview: String,
    var isFavorite: Boolean
)