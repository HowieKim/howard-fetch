package com.howie.fetch.data.remote.sources

import com.howie.fetch.data.remote.models.FetchJson
import com.howie.fetch.data.remote.rest.FetchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultRemoteDataSource(
    private val fetchService: FetchService,
    private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun getFetchData(): List<FetchJson> {
        return withContext(ioDispatcher) {
            fetchService.getFetchData()
        }
    }
}