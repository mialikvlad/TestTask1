package com.example.examapp.users.combinedlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryListFragment : BaseFragment<FragmentRepositoryListBinding>() {

    private val viewModel: RepositoryListViewModel by viewModels()

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}