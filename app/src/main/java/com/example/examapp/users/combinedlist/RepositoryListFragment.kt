package com.example.examapp.users.combinedlist

import android.os.Bundle
import android.view.View
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding

class RepositoryListFragment : BaseFragment<FragmentRepositoryListBinding>() {

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}