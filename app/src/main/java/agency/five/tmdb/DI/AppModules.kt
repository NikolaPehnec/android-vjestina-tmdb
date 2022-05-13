package agency.five.tmdb.DI

import agency.five.tmdb.repository.MockDB
import agency.five.tmdb.repository.MovieApi
import agency.five.tmdb.repository.MovieApiImpl
import org.koin.dsl.module

val movieApi = module {
    single<MovieApi> { MovieApiImpl() }
}
val mockDB = module {
    single<MockDB> { MockDB() }
}