package agency.five.tmdb.repository

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(private val movieApi: MovieApi, private val mockDB: MockDB) :
    MoviesRepository {

    private val sharingScope = CoroutineScope(Dispatchers.Default)

    private val allMovies = flow { emit(mockDB.movies) }
        .onStart {
            movieApi.getAllMovies().movies?.let {
                emit(it)
                // use this in future
                // mockDB.saveMovies(it)
            }
        }.shareIn(
            sharingScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            replay = 1
        )

    private val allCategories = flow { emit(mockDB.categories) }
        .onStart {
            movieApi.getAllCategories().categories?.let {
                emit(it)
                // use this in future
                // mockDB.saveCategory(it)
            }
        }.shareIn(
            sharingScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            replay = 1
        )

    override fun getMovies(): Flow<List<MovieModel>> = allMovies

    override fun getCategories(): Flow<List<MovieCategoryModel>> = allCategories

    override fun getMovieByID(id: Long): Flow<MovieModel> {
        return allMovies.map { it.first { movie -> movie.id == id } }
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