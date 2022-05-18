package agency.five.tmdb.viewModel

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class MovieDetailViewModel(private val repository: MoviesRepository, private val movieId: Long) :
    ViewModel() {

    fun getMovieByID(): Flow<MovieModel?> {
        return repository.getMovieByID(movieId)
    }

    fun getMovieCredits(): Flow<List<CastModel>> {
        return repository.getMovieCredits(movieId)
    }
}