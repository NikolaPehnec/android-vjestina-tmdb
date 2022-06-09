package agency.five.tmdb.viewModel

import agency.five.tmdb.data.Category
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.repository.MoviesRepository
import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {


    fun getMoviesBasedOnCategory(category: Category): Flow<List<MovieModel>> {
        return when (category) {
            Category.UPCOMING -> repository.getUpcomingMovies()

            Category.WHATS_POPULAR -> repository.getPopularMovies()

            Category.NOW_PLAYING -> repository.getNowPlayingMovies()
        }
    }


}


