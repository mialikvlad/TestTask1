package com.example.examapp.users.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.RepositoryModel

class RepoDiffUtil(
    private val oldList: List<RepositoryModel>,
    private val newList: List<RepositoryModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].repositoryName == newList[newItemPosition].repositoryName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].repositoryName != newList[newItemPosition].repositoryName -> false
            oldList[oldItemPosition].ownerName != newList[newItemPosition].ownerName -> false
            oldList[oldItemPosition].ownerAvatar != newList[newItemPosition].ownerAvatar -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
            oldList[oldItemPosition].webService != newList[newItemPosition].webService -> false
            else -> true
        }
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}