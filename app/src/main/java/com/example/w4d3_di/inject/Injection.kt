package com.example.w4d3_di.inject

import android.content.Context
import com.example.w4d3_di.db.AppDatabase
import com.example.w4d3_di.model.network.UrbanRepository
import com.example.w4d3_di.model.network.UrbanRepositoryImpl
import com.example.w4d3_di.model.network.remote.UrbanRestService

class Injection {
    private var userRestService: UrbanRestService? = null
    private var dataBaseInstance: AppDatabase?= null

    fun provideUserRepo(context: Context): UrbanRepository {
        return UrbanRepositoryImpl(provideUrbanRestService(), provideAppDatabase(context))
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        if (dataBaseInstance == null) {
            dataBaseInstance = AppDatabase.getDatabaseInstance(context)
        }
        return dataBaseInstance!!
    }

    private fun provideUrbanRestService(): UrbanRestService {
        if (userRestService == null) {
            userRestService = UrbanRestService.instance
        }
        return userRestService as UrbanRestService
    }
}