package com.howie.fetch.data.repo

import com.howie.fetch.data.repo.models.FetchDomainData
import kotlinx.coroutines.flow.Flow

interface FetchRepo {
    fun getAllData() : Flow<List<FetchDomainData>>
}