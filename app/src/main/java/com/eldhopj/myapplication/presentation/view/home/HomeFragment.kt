package com.eldhopj.myapplication.presentation.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.eldhopj.myapplication.databinding.FragmentHomeBinding
import com.eldhopj.myapplication.utils.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchNews("tesla", "publishedAt")
        observeNews()
    }

    private fun observeNews() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            logcat("news_data") { it.toString() }
        }
    }
}
