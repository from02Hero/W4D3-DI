package com.example.w4d3_di.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.w4d3_di.model.local.WordDAO
import com.example.w4d3_di.model.response.Word

@Database(entities = [Word::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDAO

    companion object {
        @Volatile
        private var databseInstance: AppDatabase? = null

        fun getDatabaseInstance(mContext: Context): AppDatabase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}
const val DB_VERSION = 1
const val DB_NAME = "wordDB.db"