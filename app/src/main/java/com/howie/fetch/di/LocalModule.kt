package com.howie.fetch.di

import android.content.Context
import androidx.room.Room
import com.howie.fetch.data.local.room.FetchDao
import com.howie.fetch.data.local.room.FetchDatabase
import com.howie.fetch.data.local.sources.DefaultLocalDataSource
import com.howie.fetch.data.local.sources.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): FetchDatabase {
        return Room.databaseBuilder(
            context,
            FetchDatabase::class.java,
            FetchDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(db: FetchDatabase): FetchDao {
        return db.dao
    }

    @Provides
    fun provideLocalDataSource(dao: FetchDao): LocalDataSource {
        return DefaultLocalDataSource(dao)
    }
}