package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getFavoriteMovies(): List<MovieModel> {
        var movies: List<MovieModel> = listOf()

        viewModelScope.launch {
            repository.favouriteMovies().collect { moviesFromFlow ->
                movies = moviesFromFlow
            }
        }
        return movies
    }

    fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean) {
        viewModelScope.launch {
            repository.markMovieFavourite(movie = movie, isFavourite = isFavourite)
        }
    }

}