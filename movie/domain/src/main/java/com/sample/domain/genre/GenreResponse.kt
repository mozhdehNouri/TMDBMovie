package com.sample.domain.genre

data class GenreResponse(
    val genres: List<GenreListResponse>
)

data class GenreListResponse(
    val id: Int,
    val name: String
)


