package agency.five.tmdb.repository

import agency.five.tmdb.data.*
import agency.five.tmdb.utils.NetworkChecker
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val mockDB: MockDB,
    private val appContext: Context
) :
    MoviesRepository {

    private fun listenPopularMovies(): Flow<List<MovieModel>> = flow {
        if (NetworkChecker.isOnline(appContext)) {
            movieApi.getPopularMovies().results.let {
                emit(it.map { it.toMovie(false, Category.WHATS_POPULAR, appContext) })
            }
        }
    }

    private fun listenNowPlayingMovies(): Flow<List<MovieModel>> = flow {
        if (NetworkChecker.isOnline(appContext)) {
            movieApi.getNowPlayingMovies().results.let {
                emit(it.map { it.toMovie(false, Category.NOW_PLAYING, appContext) })
            }
        }
    }

    private fun listenUpcomingMovies(): Flow<List<MovieModel>> = flow {
        if (NetworkChecker.isOnline(appContext)) {
            movieApi.getNowPlayingMovies().results.let {
                emit(it.map { it.toMovie(false, Category.UPCOMING, appContext) })
            }
        }
    }


    override fun getPopularMovies(): Flow<List<MovieModel>> {
        val movies = mockDB.movies

        return listenPopularMovies()
            .onStart {
                emit(movies)
            }
            .onEach { moviesDbOrApi ->
                for (m in moviesDbOrApi) {
                    if (!mockDB.movies.any { it.id == m.id })
                        mockDB.movies.add(m)
                }
            }.flowOn(Dispatchers.Default)
    }

    override fun getNowPlayingMovies(): Flow<List<MovieModel>> {
        val movies = mockDB.movies

        return listenNowPlayingMovies()
            .onStart {
                emit(movies)
            }
            .onEach { moviesDbOrApi ->
                for (m in moviesDbOrApi) {
                    if (!mockDB.movies.any { it.id == m.id })
                        mockDB.movies.add(m)
                }
            }.flowOn(Dispatchers.Default)
    }

    override fun getUpcomingMovies(): Flow<List<MovieModel>> {
        val movies = mockDB.movies

        return listenUpcomingMovies()
            .onStart {
                emit(movies)
            }
            .onEach { moviesDbOrApi ->
                for (m in moviesDbOrApi) {
                    if (!mockDB.movies.any { it.id == m.id })
                        mockDB.movies.add(m)
                }
            }.flowOn(Dispatchers.Default)
    }

    private fun listenMovieByID(id: Long): Flow<MovieModel> = flow {
        if (NetworkChecker.isOnline(appContext)) {
            movieApi.getMovieByID(id)?.let {
                emit(it.toMovie(true, ""))
            }
        }
    }


    override fun getMovieByID(id: Long): Flow<MovieModel> {
        val movieDb = mockDB.movies.firstOrNull() { it.id == id }

        return listenMovieByID(id)
            .onStart {
                if (movieDb != null && !NetworkChecker.isOnline(appContext)) emit(movieDb)
            }.onEach {
                if (movieDb == null) {
                    val index = mockDB.movies.indexOfFirst { it.id == id }
                    if (index != -1)
                        mockDB.movies[index] = it
                }
            }.flowOn(Dispatchers.Default)
    }

    override fun getMovieCredits(id: Long): Flow<List<CastModel>> {
        val movieDb = mockDB.movies.firstOrNull { it.id == id }

        return flow {
            if (NetworkChecker.isOnline(appContext)) {
                val movieCreditsResponse = movieApi.getMovieCredits(id)
                val credits = mutableListOf<MovieCreditsModel>()
                credits.addAll(movieCreditsResponse.crew)
                credits.addAll(movieCreditsResponse.cast)

                emit(credits.map { it.toCrewModel() })
            }
        }.onStart {
            if (movieDb != null && movieDb.castAndCew.isNotEmpty()) {
                emit(movieDb.castAndCew)
            }
        }.onEach { movieCredits ->
            mockDB.movies.firstOrNull { it.id == id }.let {
                if (it != null) {
                    it.castAndCew = movieCredits
                }
            }
        }.flowOn(Dispatchers.Default)
    }

    override fun queryMovies(query: String): Flow<List<MovieModel>> = flow {
        movieApi.queryMovies(query).results.let {
            if (it.isNotEmpty())
                emit(it.map { it.toMovie(false, Category.UPCOMING, appContext = appContext) })
        }
    }.flowOn(Dispatchers.Default)

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