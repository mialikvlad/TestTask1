package com.example.examapp.users.details

import android.os.Bundle
import android.view.View
import com.example.examapp.base.BaseFragment
import com.example.examapp.databinding.FragmentRepositoryDetailsBinding

class RepositoryDetailsFragment : BaseFragment<FragmentRepositoryDetailsBinding>() {

    override fun getViewBinding() = FragmentRepositoryDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}