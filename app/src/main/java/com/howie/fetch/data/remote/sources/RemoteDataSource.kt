package com.howie.fetch.data.remote.sources

import com.howie.fetch.data.remote.models.FetchJson

interface RemoteDataSource {
    suspend fun getFetchData() : List<FetchJson>
}