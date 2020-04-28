package com.example.w4d3_di.viewmodel

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.w4d3_di.model.network.UrbanRepository
import com.example.w4d3_di.view.WordsAdapter
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import io.reactivex.disposables.CompositeDisposable
import rx.Notification
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class UrbanViewModel(
    private val urbanRepository: UrbanRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val progressBarVisibilityMutableLiveData = MutableLiveData<Int>()
    val progressBarVisibilityLiveData: LiveData<Int>
        get() = progressBarVisibilityMutableLiveData

    private val listVisibilityMutableLiveData = MutableLiveData<Int>()
    val listVisibilityLiveData: LiveData<Int>
        get() = listVisibilityMutableLiveData
    private val errorVisibilityMutableLiveData = MutableLiveData<Int>()
    val errorVisibilityLiveData: LiveData<Int>
        get() = errorVisibilityMutableLiveData
    private val errorMessageMutableLiveData = MutableLiveData<String>()
    val errorMessagLiveData: LiveData<String>
        get() = errorMessageMutableLiveData

    private var loaded = false

    val wordsAdapter = WordsAdapter()

    private fun getDefinitions(term: String) {
        displayLoading()
        disposable.add(
            urbanRepository
                .getDefinitionList(term)
                .subscribe({
                    loaded = true
                    if (it.isEmpty()) {
                        displayMessage("No Definitions Retrieved")
                    } else {
                        wordsAdapter.update(it)
                        displayWords()
                    }
                }, {
                    loaded = true
                    //errors
                    val errorString = when (it) {
                        is UnknownHostException -> "No Internet Connection"
                        else -> it.localizedMessage
                    }
                    displayMessage(errorString)
                })
        )
    }

    fun initialState() {
        progressBarVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.GONE
    }

    private fun displayWords() {
        // set correct visible element
        listVisibilityMutableLiveData.value = View.VISIBLE
        initialState()
    }

    private fun displayLoading() {
        // set correct visible element
        progressBarVisibilityMutableLiveData.value = View.VISIBLE
        listVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.GONE
    }

    private fun displayMessage(message: String) {
        // set correct visible element
        progressBarVisibilityMutableLiveData.value = View.GONE
        listVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.VISIBLE
        //set message
        errorMessageMutableLiveData.value = message
    }

    fun searchDefinitions(searchView: SearchView) {
        RxSearchView.queryTextChanges(searchView)
            .doOnEach { notification: Notification<in CharSequence?> ->
                val query = notification.value as CharSequence?
                if (query != null && query.length > 4) {
                    getDefinitions(query.toString())
                }
            }
            .debounce(
                300,
                TimeUnit.MILLISECONDS
            ) // to skip intermediate letters
            .retry(3)
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}