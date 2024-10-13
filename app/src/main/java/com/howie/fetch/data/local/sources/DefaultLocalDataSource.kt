package com.howie.fetch.data.local.sources

import com.howie.fetch.data.local.room.FetchDao
import com.howie.fetch.data.local.room.FetchEntity

class DefaultLocalDataSource(private val fetchDao: FetchDao): LocalDataSource {

    override suspend fun insertData(entityList: List<FetchEntity>) {
        fetchDao.insert(entityList)
    }

    override suspend fun getData(): List<FetchEntity> {
        return fetchDao.getData()
    }

    override suspend fun deleteAllData() {
        fetchDao.deleteAll()
    }
}