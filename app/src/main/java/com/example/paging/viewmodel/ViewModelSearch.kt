package com.example.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.paging.local.Repository
import com.example.paging.model.RepoModel
import com.example.paging.paging.RepoResult

class ViewModelSearch(private val repository: Repository) : ViewModel(){
    private val queryLiveData = MutableLiveData<String>()
    private val repoResult : LiveData<RepoResult> = Transformations.map(queryLiveData){
        repository.search(it)
    }

    val repos : LiveData<PagedList<RepoModel>> = Transformations.switchMap(repoResult){
            it -> it.data
    }
    val networkErrors : LiveData<String> = Transformations.switchMap(repoResult){
            it -> it.networkErrors
    }

    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    fun lastQueryValue(): String? = queryLiveData.value
}