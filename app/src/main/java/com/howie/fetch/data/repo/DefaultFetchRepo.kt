package com.howie.fetch.data.repo

import com.howie.fetch.data.local.sources.LocalDataSource
import com.howie.fetch.data.remote.sources.RemoteDataSource
import com.howie.fetch.data.repo.mappers.DomainMapper.asDomainModel
import com.howie.fetch.data.repo.mappers.DomainMapper.asLocalModel
import com.howie.fetch.data.repo.models.FetchDomainData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultFetchRepo(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : FetchRepo {
    override fun getAllData(): Flow<List<FetchDomainData>> {
        return flow {
            val cached = localDataSource.getData()
            if (cached.isNotEmpty()) {
                emit(cached.map { it.asDomainModel() })
            }
            val data = remoteDataSource.getFetchData().map { it.asDomainModel() }
            localDataSource.insertData(data.map { it.asLocalModel() })
            emit(data)
        }

    }
}