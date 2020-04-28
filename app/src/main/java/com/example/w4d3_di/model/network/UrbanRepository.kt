package com.example.w4d3_di.model.network

import com.example.w4d3_di.model.response.Word
import io.reactivex.Single

interface UrbanRepository {
    fun getDefinitionList(term: String): Single<MutableList<Word>>
}