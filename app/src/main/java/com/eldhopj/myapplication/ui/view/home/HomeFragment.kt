package com.eldhopj.myapplication.ui.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.eldhopj.myapplication.databinding.FragmentHomeBinding
import com.eldhopj.myapplication.ui.base.BaseFragment
import com.eldhopj.myapplication.utils.network.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

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
            when (it) {
                is NetworkResponse.Loading -> {

                }
                is NetworkResponse.Success -> {
                    it.data?.let {
                        Log.d("observeNews", it.toString())
                    }
                }
                is NetworkResponse.Error -> {
                    Log.d("observeNews", it.message)
                }
            }
        }
    }
}
