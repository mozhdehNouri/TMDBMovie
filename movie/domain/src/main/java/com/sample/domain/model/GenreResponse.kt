package com.sample.domain.model

data class GenreResponse(
    val genres: List<GenreListResponse>
)

data class GenreListResponse(
    val id: Int,
    val name: String
)


