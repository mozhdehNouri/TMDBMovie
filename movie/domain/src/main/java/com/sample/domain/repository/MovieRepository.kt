package com.sample.domain.repository

import com.sample.core.comon.BaseResult
import com.sample.domain.genre.GenreResponse
import com.sample.domain.movie.MovieResponse

interface MovieRepository {

    suspend fun getGenre(): BaseResult<GenreResponse>

    suspend fun getAllMovie(): BaseResult<MovieResponse>

}