package agency.five.tmdb.data

import agency.five.tmdb.R

enum class Category(resourceId: Int, tags: List<String>) {
    WHATS_POPULAR(R.string.WHATS_POPULAR, listOf("Top rated", "Popular")),
    NOW_PLAYING(R.string.NOW_PLAYING, listOf("Streaming", "On TV", "For Rent", "In theaters")),
    UPCOMING(R.string.UPCOMING, listOf("Today", "This Week"));

    val resourceId = resourceId
    val tags = tags
}

