package com.sample.tmdbmovie.ui.data

import com.sample.domain.model.GenreListResponse
import com.sample.domain.model.GenreResponse

fun GenreResponse.toGenreUiResponse() =
    MovieUiState.GenreUi(genres.map {
        it.toGenreUiListResponse()
    })

fun GenreListResponse.toGenreUiListResponse() =
    GenreListUi(id = id, name = name)