package agency.five.tmdb.repository

interface MovieApi {

    suspend fun getPopularMovies(): MovieListResponse
    suspend fun getNowPlayingMovies(): MovieListResponse
    suspend fun getUpcomingMovies(): MovieListResponse
    suspend fun getTopRatedMovies(): MovieListResponse
    suspend fun getMovieByID(id: Long): MovieDetailResponse
    suspend fun getMovieCredits(id: Long): MovieCreditsResponse
    suspend fun queryMovies(query: String): MovieListResponse

}

public const val BASE_URL = "https://api.themoviedb.org/3"
public const val API_KEY = "f3263cb5646fd80a91685d05da9508aa"
