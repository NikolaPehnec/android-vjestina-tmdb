package agency.five.tmdb.data

import agency.five.tmdb.repository.CastMember
import agency.five.tmdb.repository.CrewMember

data class CastModel(
    val nameSurname: String,
    val roleName: String,
    val imageUrl: String,
    val castOrCrew:String
)

const val BASE_IMAGE_URL2 = "https://image.tmdb.org/t/p/w200"

fun CastMember.toCrewModel() = CastModel(
    name,
    roleName,
    picture?.let { "$BASE_IMAGE_URL2/$it" }.toString(),
    "cast"
)

fun CrewMember.toCrewModel() = CastModel(
    name,
    department,
    "",
    "crew"
)

