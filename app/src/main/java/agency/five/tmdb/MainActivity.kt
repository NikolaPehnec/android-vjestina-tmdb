package agency.five.tmdb

import agency.five.tmdb.navigation.BottomNavigationBar
import agency.five.tmdb.navigation.NavigationSetup
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TmdbTheme {
                Surface(color = MaterialTheme.colors.secondary) {
                    val navController = rememberNavController()
                    val scaffoldState: ScaffoldState = rememberScaffoldState();

                    val (canPop, setCanPop) = remember { mutableStateOf(false) }
                    navController.addOnDestinationChangedListener { controller, navDestination, _ ->
                        //If the destination isn't home, show back button
                        setCanPop(!navDestination.route.equals("home"))
                    }

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                content = {
                                    if (canPop) {
                                        IconButton(onClick = { navController.navigateUp() }) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                    Image(
                                        alignment = Alignment.TopCenter,
                                        painter = painterResource(id = R.drawable.tmdb),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }
                    ) {
                        NavigationSetup(navController = navController)
                    }
                }
            }
        }
    }
}


