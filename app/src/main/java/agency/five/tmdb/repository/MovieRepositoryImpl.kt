package agency.five.tmdb.repository

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(private val movieApi: MovieApi, private val mockDB: MockDB) :
    MoviesRepository {

    override fun getMovies(): Flow<List<MovieModel>> {
        return flow {
            movieApi.getAllMovies().movies?.let {
                emit(
                    it
                )
            }
        }
    }

    override fun getCategories(): Flow<List<MovieCategoryModel>> {
        return flow {
            movieApi.getAllCategories().categories?.let {
                emit(
                    it
                )
            }
        }
    }

    override fun getMovieByID(id: Long): Flow<MovieModel> {
        return flow {
            movieApi.getMovieByID(id)?.movie?.let {
                emit(
                    it
                )
            }
        }
    }

    private val favouriteMoviesPublisher = MutableSharedFlow<List<MovieModel>>()

    private val favouriteMoviesInitialFlow = flow { emit(mockDB.getFavoriteMovies()) }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1,
        )

    private val favouriteMoviesFlow = merge(
        favouriteMoviesPublisher,
        favouriteMoviesInitialFlow,
    )

    override fun favouriteMovies(): Flow<List<MovieModel>> = favouriteMoviesFlow


    override suspend fun markMovieFavourite(movie: MovieModel, isFavourite: Boolean) {
        mockDB.saveIsFavourite(movie = movie, isFavourite = isFavourite)

        val movies = favouriteMoviesFlow.first() as MutableList
        if (isFavourite) {
            movies.add(movie)
        } else {
            movies.remove(movie)
        }
        favouriteMoviesPublisher.emit(movies)
    }

}