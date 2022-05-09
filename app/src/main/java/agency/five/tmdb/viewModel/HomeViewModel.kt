package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getAllCategories(): Flow<List<MovieCategoryModel>> {
        return repository.getCategories()
    }

    fun getAllMovies(): Flow<List<MovieModel>> {
        return repository.getMovies()
    }

}