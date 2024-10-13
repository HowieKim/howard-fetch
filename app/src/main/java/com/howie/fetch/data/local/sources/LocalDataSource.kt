package com.howie.fetch.data.local.sources

import com.howie.fetch.data.local.room.FetchEntity

interface LocalDataSource {
    suspend fun insertData(entityList: List<FetchEntity>)
    suspend fun getData(): List<FetchEntity>
    suspend fun deleteAllData()
}