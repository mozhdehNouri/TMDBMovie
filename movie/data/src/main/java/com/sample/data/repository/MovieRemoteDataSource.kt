package com.sample.data.repository

import com.sample.core.comon.BaseResult
import com.sample.data.model.genre.GenreNetworkResponse
import com.sample.data.model.movie.MovieNetworkResponse

interface MovieRemoteDataSource {

    suspend fun getGenre(): BaseResult<GenreNetworkResponse>
    suspend fun getAllMovie(): BaseResult<MovieNetworkResponse>

}