package agency.five.tmdb.navigation

import agency.five.tmdb.FavoriteScreen
import agency.five.tmdb.HomeScreen
import agency.five.tmdb.MovieDetailScreen
import agency.five.tmdb.viewModel.FavoriteMoviesViewModel
import agency.five.tmdb.viewModel.HomeViewModel
import agency.five.tmdb.viewModel.MovieDetailViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationSetup(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    favoriteViewModel: FavoriteMoviesViewModel,
    movieDetailViewModel: MovieDetailViewModel,
) {

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(homeViewModel, favoriteViewModel) { movieId ->
                navController.navigate("movieDetailScreen/$movieId")
            }
        }
        composable(BottomNavItem.Favorite.route) {
            FavoriteScreen(favoriteViewModel) { movieId ->
                navController.navigate("movieDetailScreen/$movieId")
            }
        }
        composable("movieDetailScreen/{movieId}", arguments = listOf(
            navArgument("movieId") {
                type = NavType.LongType
            }
        )) {
            MovieDetailScreen(it.arguments?.getLong("movieId")!!, movieDetailViewModel)
        }
    }

}

