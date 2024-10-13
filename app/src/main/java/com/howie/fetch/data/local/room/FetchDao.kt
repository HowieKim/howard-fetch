package com.howie.fetch.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FetchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityList: List<FetchEntity>)

    @Query("SELECT * FROM fetch_table")
    suspend fun getData(): List<FetchEntity>

    @Query("DELETE FROM fetch_table")
    suspend fun deleteAll()
}