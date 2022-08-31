package com.example.examapp.repos.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.examapp.R
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryDetailsBinding

class RepositoryDetailsFragment : BaseFragment<FragmentRepositoryDetailsBinding>() {

    private val viewModel: RepositoryDetailsViewModel by viewModels()

    override fun getViewBinding() = FragmentRepositoryDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setupWithNavController(findNavController())

            viewModel.ownerNameState.observe(viewLifecycleOwner) { ownerName ->
                textName.text = ownerName
            }

            viewModel.repoNameState.observe(viewLifecycleOwner) { repoName ->
                textRepository.text = repoName
            }

            viewModel.webServiceState.observe(viewLifecycleOwner) { webService ->
                textWebservice.text = webService
                webserviceImage.setImageResource(
                    if (webService == GITHUB_SERVICE) R.drawable.ic_iconmonstr_github
                    else R.drawable.ic_bitbucket_icon
                )
            }

            viewModel.avatarUrlState.observe(viewLifecycleOwner) { avatarUrl ->
                ownerImage.load(avatarUrl) {
                    scale(Scale.FIT)
                    size(ViewSizeResolver(root))
                }
            }

            viewModel.descriptionState.observe(viewLifecycleOwner) { description ->
                textDescription.text = description
            }
        }
    }

    companion object {
        private const val GITHUB_SERVICE = "Github"
    }
}