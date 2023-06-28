package com.sample.domain.movie

import com.sample.domain.repository.MovieRepository
import javax.inject.Inject

class AllMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun invoke() = repository.getAllMovie()

}