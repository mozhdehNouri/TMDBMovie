package com.sample.data.repository

import com.sample.data.model.genre.GenreNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("genre/movie/list")
    suspend fun getMovieGenre(): Response<GenreNetworkResponse>


}