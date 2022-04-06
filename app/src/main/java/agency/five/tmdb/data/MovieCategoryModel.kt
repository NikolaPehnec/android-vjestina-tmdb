package agency.five.tmdb.data

data class MovieCategoryModel(
    val categoryId: Long,
    val categoryName: String,
    val tags: List<String>,
    var movies: List<MovieModel>
)