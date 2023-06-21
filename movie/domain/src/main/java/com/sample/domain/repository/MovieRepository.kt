package com.sample.domain.repository

import com.sample.core.comon.BaseResult
import com.sample.domain.model.GenreResponse

interface MovieRepository {

    suspend fun getGenre(): BaseResult<GenreResponse>

}