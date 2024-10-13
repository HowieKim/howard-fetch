package com.howie.fetch.di

import com.howie.fetch.data.remote.rest.FetchService
import com.howie.fetch.data.remote.sources.DefaultRemoteDataSource
import com.howie.fetch.data.remote.sources.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideRemoteDataSource(
        blahFetchService: FetchService,
        @IoDispatcher
        ioDispatcher: CoroutineDispatcher
    ): RemoteDataSource {
        return DefaultRemoteDataSource(blahFetchService, ioDispatcher)
    }
}