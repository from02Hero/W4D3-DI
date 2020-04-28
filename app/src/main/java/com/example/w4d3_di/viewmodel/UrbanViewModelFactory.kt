package com.example.w4d3_di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.w4d3_di.model.network.UrbanRepository

class UrbanViewModelFactory(private val urbanRepository: UrbanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UrbanViewModel(urbanRepository) as T
    }
}