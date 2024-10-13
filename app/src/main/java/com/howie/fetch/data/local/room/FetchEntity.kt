package com.howie.fetch.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fetch_table")
data class FetchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val listId: Int,
    val name: String? = null,
)
