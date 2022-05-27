package agency.five.tmdb.repository

import agency.five.tmdb.data.Category
import agency.five.tmdb.data.MovieModel
import agency.five.tmdb.data.toCrewModel
import agency.five.tmdb.data.toMovie
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val mockDB: MockDB,
    private val appContext: Context
) :
    MoviesRepository {

    private val sharingScope = CoroutineScope(Dispatchers.Default)

    private val popularMovies = flow { emit(mockDB.movies) }
        .onStart {
            movieApi.getPopularMovies().results.let {
                emit(it.map { it.toMovie(false, Category.WHATS_POPULAR, appContext) })
            }
        }.shareIn(
            sharingScope,
            SharingStarted.Lazily,
            replay = 1
        )

    /*  private val popularMovies = flow {
          movieApi.getNowPlayingMovies().results.let { it ->
              emit(it.map { it.toMovie(false, Category.WHATS_POPULAR, appContext) })
          }
      }.flatMapConcat { movies ->
          flow {
              emit(mockDB.movies.filter { movieDb -> movies.any { it.id != movieDb.id } })
          }
      }*/


    private val nowPlayingMovies = flow {
        movieApi.getNowPlayingMovies().results.let {
            emit(it.map { it.toMovie(false, Category.NOW_PLAYING, appContext) })
        }
    }

    private val upcomingMovies = flow {
        movieApi.getUpcomingMovies().results.let {
            emit(it.map { it.toMovie(false, Category.UPCOMING, appContext) })
        }
    }

    override fun getPopularMovies(): Flow<List<MovieModel>> = popularMovies


    override fun getNowPlayingMovies(): Flow<List<MovieModel>> = nowPlayingMovies

    override fun getUpcomingMovies(): Flow<List<MovieModel>> = upcomingMovies


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
            emit(mockDB.movies.first { it.id == id }.castAndCew.toImmutableList())
        }
    }.onStart {
        val movieCreditResponse = movieApi.getMovieCredits(id)

        movieCreditResponse.cast.let { emit(it.map { it.toCrewModel() }) }
    }.shareIn(
        sharingScope,
        SharingStarted.Lazily,
        replay = 1
    )

    override fun queryMovies(query: String): Flow<List<MovieModel>> = flow {
        movieApi.queryMovies(query).results.let {
            emit(it.map { it.toMovie(false, Category.UPCOMING, appContext = appContext) })
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