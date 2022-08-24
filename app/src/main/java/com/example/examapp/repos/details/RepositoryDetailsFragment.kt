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
import dagger.hilt.android.AndroidEntryPoint

class RepositoryDetailsFragment : BaseFragment<FragmentRepositoryDetailsBinding>() {

    private val viewModel: RepositoryDetailsViewModel by viewModels()

    override fun getViewBinding() = FragmentRepositoryDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setupWithNavController(findNavController())
            ownerImage.load(viewModel.getAvatarUrl()){
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            webserviceImage.setImageResource(
                if (viewModel.getWebService() == "Github") R.drawable.ic_iconmonstr_github
                else R.drawable.ic_bitbucket_icon
            )
            textName.text = viewModel.getOwnerName()
            textRepository.text = viewModel.getRepoName()
            textWebservice.text = viewModel.getWebService()
            textDescription.text = viewModel.getDescription()
        }
    }
}