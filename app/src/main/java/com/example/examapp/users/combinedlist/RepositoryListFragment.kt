package com.example.examapp.users.combinedlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding
import com.example.examapp.users.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryListFragment : BaseFragment<FragmentRepositoryListBinding>() {

    private val viewModel: RepositoryListViewModel by viewModels()

    private val adapter by lazy {
        RepoAdapter { repoModel ->
            findNavController().navigate(
                RepositoryListFragmentDirections.toRepositoryDetails(
                    repoModel.description
                )
            )
        }
    }

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            viewLifecycleOwner.lifecycleScope.launch {
                adapter.setData(viewModel.getRepos())
            }
        }
    }
}