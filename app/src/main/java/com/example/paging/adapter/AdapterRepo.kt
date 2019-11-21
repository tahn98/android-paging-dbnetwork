package com.example.paging.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.model.RepoModel

class AdapterRepo : PagedListAdapter<RepoModel, RecyclerView.ViewHolder>(
    REPO_COMPARATOR
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderRepo.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if(repoItem != null){
            (holder as ViewHolderRepo).bind(repoItem)
        }
    }

    companion object{
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RepoModel>(){
            override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
                oldItem.fullName == newItem.fullName
            override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
                oldItem == newItem
        }
    }
}