package agency.five.tmdb.navigation

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.Blue
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector,
    val icon2: ImageVector,
) {
    object Home : BottomNavItem(
        route = Screen.HomeScreen.route,
        titleResId = R.string.title_home_screen,
        icon = Icons.Default.Home,
        icon2 = Icons.Default.Home
    )

    object Favorite : BottomNavItem(
        route = Screen.FavoriteScreen.route,
        titleResId = R.string.title_favorite_screen,
        icon = Icons.Default.FavoriteBorder,
        icon2 = Icons.Default.Favorite
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite
    )

    BottomNavigation(
        contentColor = Blue, backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.icon2 else item.icon,
                        stringResource(id = item.titleResId)
                    )
                },
                label = { Text(stringResource(id = item.titleResId)) },
                selected = currentRoute == item.route,
                selectedContentColor = Blue,
                unselectedContentColor = Color.LightGray,
                alwaysShowLabel = true,

                onClick = {
                    navController.navigate(item.route) {

                        //Popam stack jer je uvjet za back strelicu da back stack nije null -> jedino kad se ucita detail
                        navController.popBackStack()
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}
