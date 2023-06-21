package com.sample.data.di

import com.sample.data.repository.MovieRemoteDataSource
import com.sample.data.repository.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
internal abstract class MovieRemoteSourceModule {

    @Binds
    abstract fun bindMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource


}