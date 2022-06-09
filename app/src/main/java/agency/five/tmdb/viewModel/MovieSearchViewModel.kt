package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class MovieSearchViewModel(private val repository: MoviesRepository, private val query: String) :
    ViewModel() {


    fun getMoviesQueried(): Flow<List<MovieModel>> {
        return repository.queryMovies(query)
    }
}