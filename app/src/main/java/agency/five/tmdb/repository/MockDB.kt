package agency.five.tmdb.repository

import agency.five.tmdb.data.CastModel
import agency.five.tmdb.data.MovieCategoryModel
import agency.five.tmdb.data.MovieModel
import java.util.*

class MockDB() {

    val movies = listOf<MovieModel>(
        MovieModel(
            675353,
            "Sonic the Hedgehog 2",
            "https://image.tmdb.org/t/p/w300/egoyMDLqCxzjnSrWOz50uLlJWmD.jpg",
            listOf("What's popular"),
            listOf("Popular"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            75f,
            Date(2008, 2, 5),
            listOf("Action", "Science Fiction", "Adventure"),
            "2h 6m",
            "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. " +
                    "His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to " +
                    "destroy civilizations.",
            false
        ),
        MovieModel(
            335787,
            "Uncharted",
            "https://image.tmdb.org/t/p/w300/tlZpSxYuBRoVJBOpUrPdQe9FmFq.jpg",
            listOf("What's popular"),
            listOf("Top rated"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            78f,
            Date(1997, 10, 24),
            listOf("Science Fiction", "Romance"),
            "1h 46m",
            "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
            false
        ),
        MovieModel(
            414906,
            "The Batman",
            "https://image.tmdb.org/t/p/w300/74xTEgt7R36Fpooo50r9T25onhq.jpg",
            listOf("What's popular"),
            listOf("Popular"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            85f,
            Date(1994, 6, 24),
            listOf("Musical", "Family"),
            "1h 29m",
            "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
            false
        ),
        MovieModel(
            629542,
            "The Bad Guys",
            "https://image.tmdb.org/t/p/w300/7qop80YfuO0BwJa1uXk1DXUUEwv.jpg",
            listOf("Now playing", "Upcoming"),
            listOf("Streaming", "On TV", "Today"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            55f,
            Date(2020, 6, 15),
            listOf("Adventure", "Family"),
            "1h 24m",
            "When the infamous Bad Guys are finally caught after years of countless heists and being the world’s most-wanted villains, Mr. Wolf brokers a deal to save them all from prison.",
            false
        ),
        MovieModel(
            883502,
            "Fortress: Sniper's Eye",
            "https://image.tmdb.org/t/p/w300/61J34xHVVdQHbJ4MSCWQo4e727v.jpg",
            listOf("Now playing"),
            listOf("Streaming"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            56f,
            Date(2013, 9, 21),
            listOf("Romance", "Drama"),
            "1h 25m",
            "Weeks after the deadly assault on Fortress Camp, Robert makes a daring rescue to save Sasha, the widow of his old nemesis Balzary. But back in the camp's command bunker, it appears Sasha may have devious plans of her own. As a new attack breaks out, Robert is confronted with a familiar face he thought he'd never see again…",
            false
        ),
        MovieModel(
            799876,
            "The Outfit",
            "https://image.tmdb.org/t/p/w300/lZa5EB6PVJBT5mxhgZS5ftqdAm6.jpg",
            listOf("Now playing"),
            listOf("On TV"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            78f,
            Date(2016, 5, 5),
            listOf("Action", "Adventure"),
            "2h 28m",
            "Leonard is an English tailor who used to craft suits on London’s world-famous Savile Row. After a personal tragedy, he’s ended up in Chicago, operating a small tailor shop in a rough part of town where he makes beautiful clothes for the only people around who can afford them: a family of vicious gangsters.",
            false
        ),
        MovieModel(
            294793,
            "All the Old Knives",
            "https://image.tmdb.org/t/p/w300/g4tMniKxol1TBJrHlAtiDjjlx4Q.jpg",
            listOf("Now playing"),
            listOf("In theatre"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            88f,
            Date(2010, 4, 29),
            listOf("Action", "Adventure"),
            "2h 28m",
            "When the CIA discovers one of its agents leaked information that cost more than 100 people their lives, veteran operative Henry Pelham is assigned to root out the mole with his former lover and colleague Celia Harrison.",
            false
        ),
        MovieModel(
            532710,
            "Firestarter",
            "https://image.tmdb.org/t/p/w300/2MTGip0nfahQ1jPQCZSfCsPBZes.jpg",
            listOf("Upcoming"),
            listOf("Today"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            63f,
            Date(2004, 11, 29),
            listOf("Science Fiction", "Action"),
            "2h 5m",
            "For more than a decade, parents Andy and Vicky have been on the run, desperate to hide their daughter Charlie from a shadowy federal agency that wants to harness her unprecedented gift for creating fire into a weapon of mass destruction. ",
            false
        ),
        MovieModel(
            361743,
            "Top Gun: Maverick",
            "https://image.tmdb.org/t/p/w300/wxP2Mzv9CdjOK6t4dNnFGqIQl0V.jpg",
            listOf("Upcoming"),
            listOf("This week"),
            listOf(
                CastModel("Don Heck", null, "Characters", null),
                CastModel("Jack Kirby", null, "Characters", null),
                CastModel("Jon Favreau", null, "Director", null),
                CastModel("Don Heck", null, "Screenplay", null),
                CastModel("Jack Macrum", null, "Screenplay", null),
                CastModel("Matt Holloway", null, "Screenplay", null),
                CastModel(
                    "Robert Downey Jr.",
                    "Tony Stark/Iron man",
                    null,
                    "image"
                ),
                CastModel(
                    "Terrence Howard",
                    "James Rhodes",
                    null,
                    "image"
                ),
                CastModel(
                    "Jeff Bridges",
                    "Obadiah Stane/Iron Monger",
                    null,
                    "image"
                )
            ),
            72f,
            Date(2013, 4, 25),
            listOf("Science Fiction", "Action"),
            "2h 10m",
            "After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him.",
            false
        )
    )

    fun saveIsFavourite(movie: MovieModel, isFavourite: Boolean) {
        this.movies.filter { _movie -> _movie.id == movie.id }.first().isFavorite = isFavourite
    }

    fun getFavoriteMovies(): List<MovieModel> {
        return movies.filter { movie -> movie.isFavorite }
    }

    val tagsWhatsPopular = listOf("Popular", "Top rated")
    val tagsNowPlaying = listOf("Streaming", "On TV", "For Rent", "In theaters")
    val tagsUpcoming = listOf("Today", "This week")

    val categories = mutableListOf(
        MovieCategoryModel(1, "What's popular", tagsWhatsPopular),
        MovieCategoryModel(2, "Now playing", tagsNowPlaying),
        MovieCategoryModel(3, "Upcoming", tagsUpcoming),
    )


}