package agency.five.tmdb.repository

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.data.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<List<MovieModel>>
    fun getUpcomingMovies(): Flow<List<MovieModel>>
    fun getNowPlayingMovies(): Flow<List<MovieModel>>
    fun favouriteMovies(): Flow<List<MovieModel>>
    fun getMovieByID(id: Long): Flow<MovieModel>
    fun getMovieCredits(id: Long): Flow<List<CastModel>>
    fun queryMovies(query: String): Flow<List<MovieModel>>
    suspend fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean)

}