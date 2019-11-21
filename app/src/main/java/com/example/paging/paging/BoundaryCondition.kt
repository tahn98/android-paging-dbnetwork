package com.example.paging.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.paging.api.GithubService
import com.example.paging.model.RepoModel
import com.example.paging.api.searchRepos
import com.example.paging.local.LocalCache

class BoundaryCondition(
    private val query: String,
    private val service: GithubService,
    private val cache: LocalCache
) : PagedList.BoundaryCallback<RepoModel>() {

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoModel) {
        requestAndSaveData(query)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(
            service = service,
            query = query,
            page = lastRequestedPage,
            itemsPerPage = NETWORK_PAGE_SIZE,
            onSuccess = { repos ->
                cache.insert(repos) {
                    lastRequestedPage++
                    isRequestInProgress = false
                }
            },
            onError = {
                isRequestInProgress = false
            })
    }
}