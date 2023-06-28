package com.sample.data.model.movie

import com.sample.domain.movie.MovieItemResponse
import com.sample.domain.movie.MovieResponse

fun MovieNetworkResponse.toMovieResponse() = MovieResponse(
    page = page,
    movieListResults = movieListResultsNetwork.map {
        it.toMovieItemResponse()
    }
)

fun MovieItemNetworkResponse.toMovieItemResponse() = MovieItemResponse(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)