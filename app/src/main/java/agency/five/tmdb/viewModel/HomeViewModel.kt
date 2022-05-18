package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movies = mutableStateListOf<MovieModel>()

    fun getAllCategories(): Flow<List<MovieCategoryModel>> {
        return repository.getCategories()
    }


    fun getAllMovies(): Flow<List<MovieModel>> {
        return repository.getMovies()
    }


}