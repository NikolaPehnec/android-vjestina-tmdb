package agency.five.tmdb.repository

import agency.five.tmdb.data.FetchingDataException
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


class MovieApiImpl(private val client: HttpClient) : MovieApi {

    override suspend fun getPopularMovies(): MovieListResponse {
        return try {
            return client.get("$BASE_URL/movie/popular?api_key=$API_KEY")
        } catch (cause: FetchingDataException) {
            MovieListResponse(emptyList(), "Error while fetching data")
        }
    }

    override suspend fun getNowPlayingMovies(): MovieListResponse {
        return try {
            return client.get("$BASE_URL/movie/now_playing?api_key=$API_KEY")
        } catch (cause: FetchingDataException) {
            MovieListResponse(emptyList(), "Error while fetching data")
        }
    }

    override suspend fun getUpcomingMovies(): MovieListResponse {
        return try {
            client.get("$BASE_URL/movie/upcoming?api_key=$API_KEY")
        } catch (cause: FetchingDataException) {
            MovieListResponse(emptyList(), "Error while fetching data")
        }
    }

    override suspend fun getMovieByID(id: Long): MovieDetailResponse? {
        return try {
            client.get("$BASE_URL/movie/$id?api_key=$API_KEY")
        } catch (cause: FetchingDataException) {
            null
        }
    }

    override suspend fun getMovieCredits(id: Long): MovieCreditsResponse {
        return try {
            client.get("$BASE_URL/movie/$id/credits?api_key=$API_KEY")
        } catch (cause: FetchingDataException) {
            MovieCreditsResponse(0, emptyList(), emptyList(), "Error while fetching data")
        }
    }

    override suspend fun queryMovies(query: String): MovieListResponse =
        client.get("$BASE_URL/search/movie?api_key=$API_KEY&query=$query")

}

@Serializable
data class MovieListResponse(
    @SerialName("results")
    val results: List<MovieResponse>,
    val error: String? = null
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
    @SerialName("vote_average")
    val vote_average: Float,
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
    val cast: List<MovieCreditsModel>,
    @SerialName("crew")
    val crew: List<MovieCreditsModel>,
    val error: String? = null
)

@Serializable
data class Genres(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class MovieCreditsModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("department")
    val department: String? = null,
    @SerialName("character")
    val roleName: String? = null,
    @SerialName("profile_path")
    val picture: String? = null
)


