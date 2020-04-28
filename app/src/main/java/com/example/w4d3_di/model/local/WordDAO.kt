package com.example.w4d3_di.model.local

import androidx.room.*
import com.example.w4d3_di.model.response.Word
import io.reactivex.Single

@Dao
interface WordDAO {

    @Query("SELECT * FROM word WHERE term = :search")
    fun getDefinitions(search: String): Single<MutableList<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words: MutableList<Word>)

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Update
    fun updateWord(word: Word)
}