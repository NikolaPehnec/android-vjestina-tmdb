package agency.five.tmdb.viewModel

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class MovieDetailViewModel(private val repository: MoviesRepository, private val movieId: Long) :
    ViewModel() {

    fun getMovieByID(): Flow<MovieModel> {
        return repository.getMovieByID(movieId)
    }

    fun getMovieCredits(): Flow<List<CastModel>> {
        return repository.getMovieCredits(movieId)
    }

    fun getCastFromMovieCredits(credits: List<CastModel>): List<CastModel> {
        return credits.filter { it.department == null }
    }

    fun getCrewFromMovieCredits(credits: List<CastModel>): List<CastModel> {
        return credits.filter { it.department != null }
    }
}