package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getMovieByID(id: Long): MovieModel? {
        var movie: MovieModel? = null

        viewModelScope.launch {
            repository.getMovieByID(id).collect { movieFromFlow ->
                movie = movieFromFlow
            }
        }
        return movie
    }
}