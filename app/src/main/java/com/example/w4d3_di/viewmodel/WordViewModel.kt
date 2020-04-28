package com.example.w4d3_di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.w4d3_di.model.response.Word

class WordViewModel : ViewModel() {
    val definition = MutableLiveData<String>()
    val uptxt = MutableLiveData<String>()
    val downtxt = MutableLiveData<String>()

    fun bind(word: Word) {
        definition.value = word.definition
        uptxt.value = word.thumbs_up.toString()
        downtxt.value = word.thumbs_down.toString()
    }

}