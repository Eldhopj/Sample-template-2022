package com.eldhopj.myapplication.presentation.view.home

import androidx.fragment.app.viewModels
import com.eldhopj.myapplication.databinding.FragmentHomeBinding
import com.eldhopj.myapplication.utils.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import logcat.logcat

/**
 * Home fragment
 *
 * @constructor Create empty Home fragment
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun viewCreated(binding: FragmentHomeBinding) {
        binding.apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.fetchNews("tesla", "publishedAt")
        observeNews()
    }

    private fun observeNews() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            logcat("news_data") { it.toString() }
        }
    }
}
