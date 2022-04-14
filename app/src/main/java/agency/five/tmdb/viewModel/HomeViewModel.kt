package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getAllCategories(): List<MovieCategoryModel> {
        var categories: List<MovieCategoryModel> = listOf()

        viewModelScope.launch {
            repository.getCategories().collect { categoriesFromFlow ->
                categories = categoriesFromFlow
            }
        }
        return categories

    }

    fun getAllMovies(): List<MovieModel> {
        var movies: List<MovieModel> = listOf()

        viewModelScope.launch {
            repository.getMovies().collect { moviesFromFlow ->
                movies = moviesFromFlow
            }
        }
        return movies
    }

}