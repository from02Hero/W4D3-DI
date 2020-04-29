package com.example.w4d3_di.inject

import android.app.Application
import androidx.room.Room
import com.example.w4d3_di.db.AppDatabase
import com.example.w4d3_di.model.local.WordDAO
import com.example.w4d3_di.model.network.UrbanRepositoryDagger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application) {
    private val demoDatabase: AppDatabase =
        Room.databaseBuilder(mApplication, AppDatabase::class.java, "demo-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return demoDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDatabase): WordDAO {
        return demoDatabase.wordDao()
    }

}