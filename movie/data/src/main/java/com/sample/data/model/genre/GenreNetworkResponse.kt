package com.sample.data.model.genre


import com.google.gson.annotations.SerializedName

data class GenreNetworkResponse(
    @SerializedName("genres")
    val genres: List<GenreItemNetworkResponse>
)

data class GenreItemNetworkResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
