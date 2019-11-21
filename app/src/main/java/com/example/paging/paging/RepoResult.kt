package com.example.paging.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.paging.model.RepoModel

data class RepoResult(
    val data: LiveData<PagedList<RepoModel>>,
    val networkErrors: LiveData<String>
)