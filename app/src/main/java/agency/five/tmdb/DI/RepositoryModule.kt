package agency.five.tmdb.DI

import agency.five.tmdb.repository.MovieRepositoryImpl
import agency.five.tmdb.repository.MoviesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    single<MoviesRepository> { MovieRepositoryImpl(get(), get(), androidContext()) }
}

