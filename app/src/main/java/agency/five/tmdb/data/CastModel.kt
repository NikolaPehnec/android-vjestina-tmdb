package agency.five.tmdb.data

import agency.five.tmdb.repository.MovieCreditsModel

data class CastModel(
    val nameSurname: String,
    val roleName: String?,
    val department: String?,
    val imageUrl: String?,
)

const val BASE_IMAGE_URL2 = "https://image.tmdb.org/t/p/w200"

fun MovieCreditsModel.toCrewModel() = CastModel(
    name,
    roleName,
    department?.let { it },
    picture?.let { "$BASE_IMAGE_URL2/$it" }.toString()
)


