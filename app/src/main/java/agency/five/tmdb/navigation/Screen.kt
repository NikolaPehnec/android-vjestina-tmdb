package agency.five.tmdb.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object FavoriteScreen : Screen("favorite")
}