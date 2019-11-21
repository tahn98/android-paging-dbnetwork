package com.example.paging.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.model.RepoModel

class ViewHolderRepo(view : View) : RecyclerView.ViewHolder(view){
    private val name : TextView = view.findViewById(R.id.repo_name)
    private val description : TextView = view.findViewById(R.id.repo_description)
    private val languageTitle : TextView = view.findViewById(R.id.title_repo_language)
    private val language: TextView = view.findViewById(R.id.repo_language)
    private val fork : TextView = view.findViewById(R.id.number_fork)
    private val star : TextView = view.findViewById(R.id.number_star)

    private var repo : RepoModel? = null

    init {
        view.setOnClickListener {
            repo?.url?.let {
                view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }
    }

    fun bind(repo: RepoModel?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
            language.visibility = View.GONE
            star.text = resources.getString(R.string.unknown)
            fork.text = resources.getString(R.string.unknown)
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: RepoModel) {
        this.repo = repo
        name.text = repo.fullName

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (repo.description != null) {
            description.text = repo.description
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility

        star.text = repo.stars.toString()
        fork.text = repo.forks.toString()

        // if the language is missing, hide the label and the value
        var languageVisibility = View.GONE
        if (!repo.language.isNullOrEmpty()) {
            val resources = this.itemView.context.resources
            language.text = resources.getString(R.string.unknown)
            languageVisibility = View.VISIBLE
        }
        language.visibility = languageVisibility
        languageTitle.visibility = languageVisibility
    }

    companion object {
        fun create(parent: ViewGroup): ViewHolderRepo {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
            return ViewHolderRepo(view)
        }
    }
}