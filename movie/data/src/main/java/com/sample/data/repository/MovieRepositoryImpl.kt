package com.sample.data.repository

import com.sample.core.comon.BaseResult
import com.sample.data.model.genre.toGenreDomain
import com.sample.domain.model.GenreResponse
import com.sample.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getGenre(): BaseResult<GenreResponse> {

        return when (val result = remoteDataSource.getGenre()) {
            is BaseResult.Success -> {
                BaseResult.Success(result.data.toGenreDomain())
            }

            is BaseResult.Error -> {
                BaseResult.Error(result.exception)
            }
        }
    }
}