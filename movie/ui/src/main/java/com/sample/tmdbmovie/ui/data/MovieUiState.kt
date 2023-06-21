package com.sample.tmdbmovie.ui.data

sealed class MovieUiState {
    data class GenreUi(
        val genres: List<GenreListUi>
    ) : MovieUiState()


}

data class GenreListUi(
    val id: Int,
    val name: String
)