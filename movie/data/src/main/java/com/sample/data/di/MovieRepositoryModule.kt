package com.sample.data.di

import com.sample.data.repository.MovieRepositoryImpl
import com.sample.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal abstract class MovieRepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImp: MovieRepositoryImpl): MovieRepository

}