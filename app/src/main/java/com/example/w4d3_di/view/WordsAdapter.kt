package com.example.w4d3_di.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.w4d3_di.R
import com.example.w4d3_di.databinding.WordItemBinding
import com.example.w4d3_di.model.response.Word

class WordsAdapter : RecyclerView.Adapter<WordViewHolder>() {

    private var words: MutableList<Word> = mutableListOf()

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bindItem(words[position])
    }

    fun update(words: MutableList<Word>) {
        this.words.clear()
        this.words = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding: WordItemBinding = DataBindingUtil.inflate(LayoutInflater.
            from(parent.context), R.layout.word_item, parent, false)
        return WordViewHolder(binding)
    }
}