package com.example.examapp.repos.combinedlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examapp.R
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryListBinding
import com.example.examapp.repos.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
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

    override fun getViewBinding() = FragmentRepositoryListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.filter_github -> {
                        // clearCompletedTasks()
                        true
                    }
                    R.id.filter_bitbucket -> {
                        // clearCompletedTasks()
                        true
                    }
                    R.id.sort_alphabetically -> {
                        // clearCompletedTasks()
                        true
                    }
                    R.id.sort_revert -> {
                        // clearCompletedTasks()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            viewLifecycleOwner.lifecycleScope.launch {
                adapter.setData(viewModel.getRepos())
            }
        }
    }
}