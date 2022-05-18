package agency.five.tmdb.DI

import agency.five.tmdb.repository.MockDB
import agency.five.tmdb.repository.MovieApi
import agency.five.tmdb.repository.MovieApiImpl
import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

val movieApi = module {
    single<MovieApi> { MovieApiImpl(httpClient) }
}
val mockDB = module {
    single<MockDB> { MockDB() }
}

val httpClient: HttpClient = HttpClient(Android) {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("HTTP", message)
            }
        }
        level = LogLevel.ALL
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
        })

    }
}