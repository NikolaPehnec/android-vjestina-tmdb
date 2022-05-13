package agency.five.tmdb.repository

interface MovieApi {

    suspend fun getAllMovies(): MovieResponse
    suspend fun getAllCategories(): MovieResponse
    suspend fun getMovieByID(id: Long): MovieResponse?

}
