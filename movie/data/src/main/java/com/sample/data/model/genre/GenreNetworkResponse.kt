package com.sample.data.model.genre


import com.google.gson.annotations.SerializedName
import com.sample.domain.model.GenreListResponse
import com.sample.domain.model.GenreResponse

data class GenreNetworkResponse(
    @SerializedName("genres")
    val genres: List<GenreListNetworkResponse>
)

data class GenreListNetworkResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun GenreNetworkResponse.toGenreDomain() = GenreResponse(genres.map {
    it.toGenreListDomain()
})

fun GenreListNetworkResponse.toGenreListDomain() =
    GenreListResponse(id = id, name = name)

