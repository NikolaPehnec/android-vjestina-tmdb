package agency.five.tmdb.data

import agency.five.tmdb.R
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class PreviewData {

    companion object data {
        fun getTagsWhatsPopular(): List<String> {
            return listOf("Popular", "Top rated")
        }

        fun getTagsNowPlaying(): List<String> {
            return listOf("Streaming", "On TV", "For Rent", "In theaters")
        }

        fun geTagsUpcoming(): List<String> {
            return listOf("Today", "This week")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCategories(): List<MovieCategoryModel> {
            return listOf(
                MovieCategoryModel(1, "What's popular", getTagsWhatsPopular()),
                MovieCategoryModel(1, "Now playing", getTagsNowPlaying()),
                MovieCategoryModel(1, "Upcoming", geTagsUpcoming()),
            )
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getMovies(): List<MovieModel> {
            return listOf(
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
                            R.drawable.terrence_howard
                        )
                    ),
                    75f,
                    Date(2008, 2, 5),
                    // LocalDate.of(2008, 2, 5),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    78f,
                    Date(1997, 10, 24),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    85f,
                    Date(1994, 6, 24),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    55f,
                    Date(2020, 6, 15),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    56f,
                    Date(2013, 9, 21),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    78f,
                    Date(2016, 5, 5),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    88f,
                    Date(2010, 4, 29),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    63f,
                    Date(2004, 11, 29),
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
                            R.drawable.terrence_howard
                        )
                    ),
                    72f,
                    Date(2013, 4, 25),
                    listOf("Science Fiction", "Action"),
                    "2h 10m",
                    "Tony Stark encounters a formidable foe called the Mandarin. After failing to defeat his enemy, Tony embarks on a journey of self-discovery as he fights against the powerful Mandarin.",
                    true
                )
            )
        }
    }

}