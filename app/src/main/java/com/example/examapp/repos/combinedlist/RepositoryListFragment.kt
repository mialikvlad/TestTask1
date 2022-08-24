package com.example.examapp.repos.combinedlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examapp.R
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding
import com.example.examapp.repos.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryListFragment : BaseFragment<FragmentRepositoryListBinding>() {

    private val viewModel: RepositoryListViewModel by viewModels()

    private val adapter by lazy {
        RepoAdapter { repoModel ->
            findNavController().navigate(
                RepositoryListFragmentDirections.toRepositoryDetails(
                    repoModel.description,
                    repoModel.ownerName,
                    repoModel.repositoryName,
                    repoModel.webService,
                    repoModel.ownerAvatar
                )
            )
        }
    }

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listRepo.observe(viewLifecycleOwner){ repoList ->
            adapter.setData(repoList)
        }

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.all_repos -> {
                        viewModel.getLoadedRepos()
                        true
                    }
                    R.id.filter_github -> {
                        viewModel.filterOnlyGithubRepos()
                        true
                    }
                    R.id.filter_bitbucket -> {
                        viewModel.filterOnlyBitbucketRepos()
                        true
                    }
                    R.id.sort_alphabetically -> {
                        viewModel.sortAlphabetically()
                        true
                    }
                    R.id.sort_revert -> {
                        viewModel.sortRevert()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}