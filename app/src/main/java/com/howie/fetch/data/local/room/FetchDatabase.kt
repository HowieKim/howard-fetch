package com.howie.fetch.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FetchEntity::class], version = 1)
abstract class FetchDatabase: RoomDatabase() {
    abstract val dao: FetchDao

    companion object {
        val DATABASE_NAME = "fetch_database"
    }
}