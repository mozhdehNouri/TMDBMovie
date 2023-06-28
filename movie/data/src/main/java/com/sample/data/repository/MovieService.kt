package com.sample.data.repository

import com.sample.data.model.genre.GenreNetworkResponse
import com.sample.data.model.movie.MovieNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenre(): Response<GenreNetworkResponse>

    @GET("/3/discover/movie")
    suspend fun getAllMovie(): Response<MovieNetworkResponse>


}