package com.sample.tmdbmovie.ui.data

import com.sample.domain.genre.GenreListResponse
import com.sample.domain.genre.GenreResponse
import com.sample.domain.movie.MovieItemResponse
import com.sample.domain.movie.MovieResponse

fun GenreResponse.toGenreUiResponse() =
    MovieUiState.GenreState(genres.map {
        it.toGenreUiListResponse()
    })

fun GenreListResponse.toGenreUiListResponse() =
    GenreItemResponseView(id = id, name = name)


fun MovieResponse.toMovieResponseView() =
    MovieUiState.MovieItemState(movieListResults.map {
        it.toMovieItemResponseView()
    })

fun MovieItemResponse.toMovieItemResponseView() =
    MovieItemResponseView(
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
