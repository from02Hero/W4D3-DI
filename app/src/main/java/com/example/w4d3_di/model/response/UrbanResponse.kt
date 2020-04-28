package com.example.w4d3_di.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class UrbanResponse(val list: MutableList<Word>)

@Entity(tableName = "word")
data class Word(@PrimaryKey(autoGenerate = true)
                var id: Int,
                @ColumnInfo(name = "term")
                var term: String,
                @ColumnInfo(name = "definition")
                val definition: String,
                @ColumnInfo(name = "thumbs_up")
                val thumbs_up: Int,
                @ColumnInfo(name = "thumbs_down")
                val thumbs_down: Int
//                ,
//                @ColumnInfo(name = "expire")
//                @TypeConverters(TimestampConverter::class)
//                var expire: Date
)