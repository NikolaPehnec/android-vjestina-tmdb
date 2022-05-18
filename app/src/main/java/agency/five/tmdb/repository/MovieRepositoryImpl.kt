package agency.five.tmdb.repository

import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.toCrewModel
import agency.five.tmdb.data.toMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(private val movieApi: MovieApi, private val mockDB: MockDB) :
    MoviesRepository {

    private val sharingScope = CoroutineScope(Dispatchers.Default)

    private val allCategories = flow { emit(mockDB.categories) }

    override fun getCategories(): Flow<List<MovieCategoryModel>> = allCategories

    private val allMovies = flow { emit(mockDB.movies) }
        .onStart {

            movieApi.getPopularMovies().results.let {
                emit(it.map { it.toMovie(false, "What's popular") })
            }

            movieApi.getNowPlayingMovies().results.let {
                emit(it.map { it.toMovie(false, "Now playing") })
            }

            movieApi.getUpcomingMovies().results.let {
                emit(it.map { it.toMovie(false, "Upcoming") })
            }

        }.shareIn(
            sharingScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
            replay = 1
        )

    override fun getMovies(): Flow<List<MovieModel>> = allMovies


    override fun getMovieByID(id: Long): Flow<MovieModel> =
        flow {
            if (mockDB.movies.firstOrNull { it.id == id } != null)
                emit(mockDB.movies.first { it.id == id })
        }
            .onStart {
                emit(movieApi.getMovieByID(id).toMovie(true, ""))
            }.shareIn(
                sharingScope,
                SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
                replay = 1
            )

    override fun getMovieCredits(id: Long) = flow {
        if (mockDB.movies.firstOrNull { it.id == id } != null) {
            emit(mockDB.movies.first { it.id == id }.cast)
            emit(mockDB.movies.first { it.id == id }.crew)
        }
    }.onStart {
        val movieCreditResponse = movieApi.getMovieCredits(id)

        movieCreditResponse.cast.let { emit(it.map { it.toCrewModel() }) }
        movieCreditResponse.crew.let { emit(it.map { it.toCrewModel() }) }
    }.shareIn(
        sharingScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000L),
        replay = 1
    )

    override fun queryMovies(query: String): Flow<List<MovieModel>> = flow {
        movieApi.queryMovies(query).results.let {
            emit(it.map { it.toMovie(false, "Upcoming") })
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