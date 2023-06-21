package com.sample.domain.useCase

import com.sample.domain.repository.MovieRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun invoke() = repository.getGenre()
}