package com.howie.fetch.data.repo.mappers

import com.howie.fetch.data.local.room.FetchEntity
import com.howie.fetch.data.remote.models.FetchJson
import com.howie.fetch.data.repo.models.FetchDomainData

object DomainMapper {

    fun FetchJson.asDomainModel(): FetchDomainData {
        return FetchDomainData(id = id, listId = listId, name = name)
    }

    fun FetchDomainData.asLocalModel() : FetchEntity {
        return FetchEntity(id = id, listId = listId, name = name)
    }

    fun FetchEntity.asDomainModel() : FetchDomainData {
        return FetchDomainData(id = id, listId = listId, name = name)
    }
}