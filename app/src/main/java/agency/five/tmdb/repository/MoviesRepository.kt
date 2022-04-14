package agency.five.tmdb.repository

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<List<MovieModel>>
    fun favouriteMovies(): Flow<List<MovieModel>>
    fun getCategories(): Flow<List<MovieCategoryModel>>
    fun getMovieByID(id: Long): Flow<MovieModel>
    suspend fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean)
}