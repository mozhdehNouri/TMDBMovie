package com.sample.tmdbmovie.ui.data

sealed class MovieUiState {
    data class GenreState(
        val genres: List<GenreItemResponseView>
    ) : MovieUiState()

    data class MovieItemState(
        val movie: List<MovieItemResponseView>
    )

}

data class GenreItemResponseView(
    val id: Int,
    val name: String
)

data class MovieItemResponseView(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)