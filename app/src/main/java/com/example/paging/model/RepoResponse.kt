package com.example.paging.model

import com.example.paging.model.RepoModel
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepoModel> = emptyList(),
    val nextPage: Int? = null
)