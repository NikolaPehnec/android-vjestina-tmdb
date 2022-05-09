package agency.five.tmdb.viewModel

import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoriteMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    fun getFavoriteMovies(): Flow<List<MovieModel>> {
        return repository.favouriteMovies()
    }

    fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean) = runBlocking {

        launch(Dispatchers.Default) {
            repository.markMovieFavourite(movie = movie, isFavourite = isFavourite)
        }
    }

}