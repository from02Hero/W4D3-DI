package com.example.w4d3_di.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4d3_di.R
import com.example.w4d3_di.databinding.ActivityMainBinding
import com.example.w4d3_di.inject.Injection
import com.example.w4d3_di.viewmodel.UrbanViewModel
import com.example.w4d3_di.viewmodel.UrbanViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: UrbanViewModel
    val injection = Injection()
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.rvNews?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(
            this,
            UrbanViewModelFactory(injection.provideUserRepo(this.applicationContext))
        ).get(UrbanViewModel::class.java)

        binding?.let {
            it.viewModel = viewModel
        }
        viewModel.initialState()
        setRecyclerViewLinearLayout()
        wordSearch()
    }

    private fun setRecyclerViewLinearLayout() {
        rvNews.layoutManager = LinearLayoutManager(this)
    }

    private fun wordSearch() {
        viewModel.searchDefinitions(word_search)
    }
}
