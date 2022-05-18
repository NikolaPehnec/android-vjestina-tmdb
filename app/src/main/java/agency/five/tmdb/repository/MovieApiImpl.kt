package agency.five.tmdb.repository

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


class MovieApiImpl(private val client: HttpClient) : MovieApi {

    override suspend fun getPopularMovies(): MovieListResponse =
        client.get("$BASE_URL/movie/popular?api_key=$API_KEY")

    override suspend fun getNowPlayingMovies(): MovieListResponse =
        client.get("$BASE_URL/movie/now_playing?api_key=$API_KEY")

    override suspend fun getUpcomingMovies(): MovieListResponse =
        client.get("$BASE_URL/movie/upcoming?api_key=$API_KEY")

    override suspend fun getTopRatedMovies(): MovieListResponse =
        client.get("$BASE_URL/movie/top_rated?api_key=$API_KEY")

    override suspend fun getMovieByID(id: Long): MovieDetailResponse =
        client.get("$BASE_URL/movie/$id?api_key=$API_KEY")

    override suspend fun getMovieCredits(id: Long): MovieCreditsResponse =
        client.get("$BASE_URL/movie/$id/credits?api_key=$API_KEY")

    override suspend fun queryMovies(query: String): MovieListResponse =
        client.get("$BASE_URL/search/movie?api_key=$API_KEY&query=$query")


}

@Serializable
data class MovieListResponse(
    @SerialName("results")
    val results: List<MovieResponse>
)

@Serializable
data class MovieResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("popularity")
    val popularity: Float,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("genre_ids")
    val genres: List<Int>,
    @SerialName("runtime")
    val runtime: Int = 0
)

@Serializable
data class MovieDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("vote_average")
    val vote_average: Float,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("genres")
    val genres: List<Genres>,
    @SerialName("runtime")
    val runtime: Int = 0
)

@Serializable
data class MovieCreditsResponse(
    @SerialName("id")
    val idMovie: Int,
    @SerialName("cast")
    val cast: List<CastMember>,
    @SerialName("crew")
    val crew: List<CrewMember>
)

@Serializable
data class Genres(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class CastMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val roleName: String,
    @SerialName("profile_path")
    val picture: String?
)

@Serializable
data class CrewMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("department")
    val department: String,
)

