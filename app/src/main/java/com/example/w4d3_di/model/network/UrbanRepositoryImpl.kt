package com.example.w4d3_di.model.network

import com.example.w4d3_di.db.AppDatabase
import com.example.w4d3_di.model.network.remote.UrbanRestService
import com.example.w4d3_di.model.response.Word
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class UrbanRepositoryImpl (private val urbanRestService: UrbanRestService,
                                                   private val appDatabase: AppDatabase) : UrbanRepository {

    override fun getDefinitionList(term: String): Single<MutableList<Word>> {
        return getDefinitionFromDB(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getDefinitionFromRemote(term: String): Single<MutableList<Word>> {
        return urbanRestService
            .getDefinitions(term)
            .map {
                it.list.forEach { word ->
                    word.term = term
                }
                println("_xyz getData from remote2 $term")
                it.list
            }
    }

    private fun getDefinitionFromDB(term: String): Single<MutableList<Word>> {
        return appDatabase
            .wordDao()
            .getDefinitions(term)
            .flatMap { localList ->
                if (localList.isEmpty()) {
                    println("_xyz noData $term")
                    getDefinitionFromRemote(term).map { remoteList ->
                        println("_xyz getData from remote1 $term")
                        cacheTerm(remoteList)
                        remoteList
                    }
                } else {
                    println("_xyz getData from db $term")
                    Single.just(localList)
                }
            }
    }

    private fun cacheTerm(words: MutableList<Word>) {
        println("_xyz cache = $words")
        appDatabase.wordDao().insertAll(words)
    }
}