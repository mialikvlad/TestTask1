package com.example.examapp.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.domain.model.RepositoryModel
import com.example.examapp.R
import com.example.examapp.databinding.ItemRepositoryBinding

class RepoAdapter(
    private val onItemClicked: (RepositoryModel) -> Unit
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    class RepoViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root)

    private var oldRepoList = emptyList<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        with(holder.binding) {
            ownerImage.load(oldRepoList[position].ownerAvatar) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textRepository.text = oldRepoList[position].repositoryName
            textUserName.text = oldRepoList[position].ownerName
            serviceImage.setImageResource(
                if (oldRepoList[position].webService == "Github") R.drawable.ic_iconmonstr_github
                else R.drawable.ic_bitbucket_icon
            )
            root.setOnClickListener {
                onItemClicked(oldRepoList[position])
            }
            textRepository.isSelected = true
        }
    }

    override fun getItemCount(): Int {
        return oldRepoList.size
    }

    fun setData(newRepoList: List<RepositoryModel>) {
        val diffUtil = RepoDiffUtil(oldRepoList, newRepoList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldRepoList = newRepoList
        diffResults.dispatchUpdatesTo(this)
    }
}