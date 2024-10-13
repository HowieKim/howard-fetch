package com.howie.fetch

import com.howie.fetch.data.local.room.FetchEntity
import com.howie.fetch.data.remote.models.FetchJson
import com.howie.fetch.data.repo.mappers.DomainMapper.asDomainModel
import com.howie.fetch.data.repo.mappers.DomainMapper.asLocalModel
import com.howie.fetch.data.repo.models.FetchDomainData
import org.junit.Assert.assertEquals
import org.junit.Test


class DataMappersTest {

    companion object {
        private const val NAME = "Test"
        private const val ID = 1L
        private const val LIST_ID = 2
    }

    @Test
    fun jsonToDomainModel() {
        val json = FetchJson(id = ID, listId = LIST_ID, name = NAME)
        val domainModel = json.asDomainModel()
        assertEquals(domainModel.id, json.id)
        assertEquals(domainModel.listId, json.listId)
        assertEquals(domainModel.name, json.name)

    }

    @Test
    fun localModelToDomainModel() {
        val localModel = FetchEntity(id = ID, listId = LIST_ID, name = NAME)
        val domainModel = localModel.asDomainModel()
        assertEquals(domainModel.id, localModel.id)
        assertEquals(domainModel.listId, localModel.listId)
        assertEquals(domainModel.name, localModel.name)
    }

    @Test
    fun domainModelToLocalModel() {
        val domainModel = FetchDomainData(id = ID, listId = LIST_ID, name = NAME)
        val localModel = domainModel.asLocalModel()
        assertEquals(localModel.id, domainModel.id)
        assertEquals(localModel.listId, domainModel.listId)
        assertEquals(localModel.name, domainModel.name)
    }
}