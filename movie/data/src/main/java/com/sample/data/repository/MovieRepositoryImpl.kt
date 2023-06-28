package com.sample.data.repository

import com.sample.core.comon.BaseResult
import com.sample.data.model.genre.toGenreDomain
import com.sample.data.model.movie.toMovieResponse
import com.sample.domain.genre.GenreResponse
import com.sample.domain.movie.MovieResponse
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

    override suspend fun getAllMovie(): BaseResult<MovieResponse> {
        return when (val result = remoteDataSource.getAllMovie()) {
            is BaseResult.Success -> {
                BaseResult.Success(result.data.toMovieResponse())
            }

            is BaseResult.Error -> {
                BaseResult.Error(result.exception)
            }
        }
    }
}