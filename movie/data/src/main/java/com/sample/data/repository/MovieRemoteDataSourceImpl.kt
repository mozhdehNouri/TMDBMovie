package com.sample.data.repository

import android.content.Context
import com.sample.core.comon.BaseRemoteDataSource
import com.sample.core.comon.BaseResult
import com.sample.data.model.genre.GenreNetworkResponse
import com.sample.data.model.movie.MovieNetworkResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val service: MovieService
) : BaseRemoteDataSource(context), MovieRemoteDataSource {

    override suspend fun getGenre(): BaseResult<GenreNetworkResponse> =
        safeApiCall {
            service.getMovieGenre()
        }

    override suspend fun getAllMovie(): BaseResult<MovieNetworkResponse> =
        safeApiCall {
            service.getAllMovie()
        }
}