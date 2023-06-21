package com.sample.data.repository

import com.sample.core.comon.BaseResult
import com.sample.data.model.genre.GenreNetworkResponse

interface MovieRemoteDataSource {

    suspend fun getGenre(): BaseResult<GenreNetworkResponse>

}