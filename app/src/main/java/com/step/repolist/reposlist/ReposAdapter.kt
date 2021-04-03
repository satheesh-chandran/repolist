package com.step.repolist.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.step.repolist.R
import com.step.repolist.models.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    fun bind(repo: Repo) {
        name.text = repo.name
    }
}

class RepoItemCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem == newItem
}

class ReposAdapter(private val repoClickHandler: (Repo) -> Unit) :
    ListAdapter<Repo, RepoViewHolder>(RepoItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo: Repo = getItem(position)
        holder.bind(repo)
        holder.itemView.setOnClickListener { repoClickHandler(repo) }
    }
}