package com.example.w4d3_di.view

import androidx.recyclerview.widget.RecyclerView
import com.example.w4d3_di.databinding.WordItemBinding
import com.example.w4d3_di.model.response.Word
import com.example.w4d3_di.viewmodel.WordViewModel

class WordViewHolder(private val binding: WordItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    private val wordViewModel = WordViewModel()

    fun bindItem(word: Word) {
        wordViewModel.bind(word)
        binding.viewModel = wordViewModel
    }
}