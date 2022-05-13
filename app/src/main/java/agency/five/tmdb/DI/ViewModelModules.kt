package agency.five.tmdb.DI

import agency.five.tmdb.viewModel.FavoriteMoviesViewModel
import agency.five.tmdb.viewModel.HomeViewModel
import agency.five.tmdb.viewModel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        FavoriteMoviesViewModel(get())
    }
    viewModel {
        MovieDetailViewModel(get(), get())
    }
}