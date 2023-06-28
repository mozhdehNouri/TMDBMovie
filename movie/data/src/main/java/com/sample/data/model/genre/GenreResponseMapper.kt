package com.sample.data.model.genre

import com.sample.domain.genre.GenreListResponse
import com.sample.domain.genre.GenreResponse

fun GenreNetworkResponse.toGenreDomain() = GenreResponse(genres.map {
    it.toGenreListDomain()
})

fun GenreItemNetworkResponse.toGenreListDomain() =
    GenreListResponse(id = id, name = name)
