package com.example.examapp.users.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.domain.model.RepositoryModel
import com.example.examapp.R
import com.example.examapp.databinding.ItemRepositoryBinding

/*
class RepoViewHolder(
    private val binding: ItemRepositoryBinding,
    private val onItemClicked: (RepositoryModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repositoryModel: RepositoryModel) {
        with(binding) {
            ownerImage.load(repositoryModel.ownerAvatar) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textRepository.text = repositoryModel.repositoryName
            textUserName.text = repositoryModel.ownerName
            serviceImage.setImageResource(
                if (repositoryModel.webService == "Github") R.drawable.ic_iconmonstr_github
                else R.drawable.ic_bitbucket_icon
            )
            root.setOnClickListener {
                onItemClicked(repositoryModel)
            }
        }
    }
}*/
