package com.howie.fetch.di

import com.howie.fetch.data.local.sources.LocalDataSource
import com.howie.fetch.data.remote.sources.RemoteDataSource
import com.howie.fetch.data.repo.DefaultFetchRepo
import com.howie.fetch.data.repo.FetchRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): FetchRepo {
        val localdata = localDataSource
        val remote = remoteDataSource
        return DefaultFetchRepo(localDataSource, remoteDataSource)
    }
}