package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getFavoriteMovies(): Flow<List<MovieModel>> {
        return repository.favouriteMovies()
    }

    fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean) =
        CoroutineScope(Dispatchers.Default).launch {
            repository.markMovieFavourite(movie = movie, isFavourite = isFavourite)
        }
}