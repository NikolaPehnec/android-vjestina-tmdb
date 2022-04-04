package agency.five.tmdb.navigation

import agency.five.tmdb.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.time.LocalDate

/**
 * Hardkodirani tu podaci, jedini nacin da spremam vrijednosti izmedu Screenova
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationSetup(navController: NavHostController) {

    val movies by remember {
        mutableStateOf(
            listOf(
                MovieModel(
                    1,
                    "Iron man",
                    R.drawable.iron_man,
                    listOf("What's popular", "Now playing"),
                    listOf("Popular", "Top rated"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    75f,
                    LocalDate.of(2008, 2, 5),
                    listOf("Action", "Science Fiction", "Adventure"),
                    "2h 6m",
                    "After being held captive in an Afghan cave, billionare engineer Tony Stark creates" +
                            "a unique weaponized suit of armor to fight evil.",
                    true
                ),
                MovieModel(
                    2,
                    "Gattaca",
                    R.drawable.gattaca,
                    listOf("What's popular"),
                    listOf("Popular"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    78f,
                    LocalDate.of(1997, 10, 24),
                    listOf("Science Fiction", "Romance"),
                    "1h 46m",
                    "A genetically inferior man assumes the identity of a superior one in order to pursue his lifelong dream of space travel.",
                    true
                ),
                MovieModel(
                    3,
                    "Lion king",
                    R.drawable.lion,
                    listOf("What's popular"),
                    listOf("Popular"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    85f,
                    LocalDate.of(1994, 6, 24),
                    listOf("Musical", "Family"),
                    "1h 29m",
                    "As a cub, Simba is forced to leave the Pride Lands after his father Mufasa is murdered by his wicked uncle, Scar. ",
                    true
                ),
                MovieModel(
                    4,
                    "Jungle beat",
                    R.drawable.jungle,
                    listOf("Now playing", "Upcoming"),
                    listOf("Streaming", "On TV", "Today"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    55f,
                    LocalDate.of(2020, 6, 15),
                    listOf("Adventure", "Family"),
                    "1h 24m",
                    "One morning, the animals of the jungle wake up to discover that they can speak. They're even more amazed when they learn the reason why: There's an alien in the jungle.",
                    true
                ),
                MovieModel(
                    5,
                    "Puppy love",
                    R.drawable.puppylove,
                    listOf("Now playing"),
                    listOf("Streaming", "On TV"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    56f,
                    LocalDate.of(2013, 9, 21),
                    listOf("Romance", "Drama"),
                    "1h 25m",
                    "A lonely teen's (Solène Rigot) friendship with a free-spirited British neighbor (Audrey Bastien) pushes her to break free of the bonds of childhood.",
                    true
                ),
                MovieModel(
                    6,
                    "Avengers: civil war",
                    R.drawable.civilwar,
                    listOf("Now playing"),
                    listOf("Streaming", "On TV", "For Rent"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    78f,
                    LocalDate.of(2016, 5, 5),
                    listOf("Action", "Adventure"),
                    "2h 28m",
                    "Friction arises between the Avengers when one group supports the government's decision to implement a law to control their powers while the other opposes it.",
                    true
                ),
                MovieModel(
                    7,
                    "Iron man 2",
                    R.drawable.ironman2,
                    listOf("Now playing"),
                    listOf("In theatre"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    88f,
                    LocalDate.of(2010, 4, 29),
                    listOf("Action", "Adventure"),
                    "2h 28m",
                    "Tony Stark is under pressure from various sources, including the government, to share his technology with the world. He must find a way to fight them while also tackling his other enemies.",
                    true
                ),
                MovieModel(
                    8,
                    "Godzilla",
                    R.drawable.godzila,
                    listOf("Upcoming"),
                    listOf("Today", "This week"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    63f,
                    LocalDate.of(2004, 11, 29),
                    listOf("Science Fiction", "Action"),
                    "2h 5m",
                    "The Earth Defence Force leaves it to Godzilla to save the Earth from sinister aliens and the monsters they control. Will Godzilla save the world?",
                    true
                ),
                MovieModel(
                    9,
                    "Iron man 3",
                    R.drawable.ironman3,
                    listOf("Upcoming"),
                    listOf("Today", "This week"),
                    listOf(
                        Writer("Don Heck", "Characters"),
                        Writer("Jack Kirby", "Characters"),
                        Writer("Jon Favreau", "Director"),
                        Writer("Don Heck", "Screenplay"),
                        Writer("Jack Macrum", "Screenplay"),
                        Writer("Matt Holloway", "Screenplay"),
                    ),
                    listOf(
                        CastModel(
                            "Robert Downey Jr.",
                            "Tony Stark/Iron man",
                            R.drawable.robert_downey
                        ),
                        CastModel(
                            "Terrence Howard",
                            "James Rhodes",
                            R.drawable.terrence_howard
                        ),
                        CastModel(
                            "Jeff Bridges",
                            "Obadiah Stane/Iron Monger",
                            R.drawable.jeff_bridges
                        )
                    ),
                    72f,
                    LocalDate.of(2013, 4, 25),
                    listOf("Science Fiction", "Action"),
                    "2h 10m",
                    "Tony Stark encounters a formidable foe called the Mandarin. After failing to defeat his enemy, Tony embarks on a journey of self-discovery as he fights against the powerful Mandarin.",
                    true
                )
            )
        )
    }

    val tagsWhatsPopular = listOf("Popular", "Top rated")
    val tagsNowPlaying = listOf("Streaming", "On TV", "For Rent", "In theaters")
    val tagsUpcoming = listOf("Today", "This week")

    val categories by remember {
        mutableStateOf(
            listOf(
                MovieCategoryModel(1, "What's popular", tagsWhatsPopular, movies),
                MovieCategoryModel(1, "Now playing", tagsNowPlaying, movies),
                MovieCategoryModel(1, "Upcoming", tagsUpcoming, movies),
            )
        )
    }

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(categories, navController)
        }
        composable(BottomNavItem.Favorite.route) {
            FavoriteScreen(movies, navController)
        }
        composable("movieDetailScreen/{movieId}", arguments = listOf(
            navArgument("movieId") {
                type = NavType.LongType
            }
        )) {
            MovieDetailScreen(it.arguments?.getLong("movieId")!!, movies)
        }
    }

}
