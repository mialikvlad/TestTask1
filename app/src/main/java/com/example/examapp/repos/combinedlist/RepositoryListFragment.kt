package com.example.examapp.repos.combinedlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examapp.R
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding
import com.example.examapp.repos.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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

    private val searchQueryFlow: Flow<String>
        get() = callbackFlow {
            val searchView =
                binding.toolbar.menu.findItem(R.id.action_search).actionView as SearchView

            val queryTextListener = object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    trySend(newText)
                    return true
                }
            }

            searchView.setOnQueryTextListener(queryTextListener)

            awaitClose {
                searchView.setOnQueryTextListener(null)
            }
        }

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listRepo.observe(viewLifecycleOwner) { repoList ->
            adapter.setData(repoList)
        }

        viewModel.circleProgressState.observe(viewLifecycleOwner) { progressState ->
            binding.progress.isVisible = progressState
        }

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            layoutSwipeRefresh.setOnRefreshListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.reloadRepos()
                    layoutSwipeRefresh.isRefreshing = false
                }
            }

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

            //TODO: doesn't work correctly
            searchQueryFlow
                .debounce(1000)
                .flatMapLatest { query ->
                    viewModel.searchByQuery(query)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}