package com.eldhopj.myapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldhopj.myapplication.model.News
import com.eldhopj.myapplication.ui.repositories.EveryThingApiRepo
import com.eldhopj.myapplication.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Home view model
 *
 * @property repoEveryThing
 * @constructor Create empty Home view model
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoEveryThing: EveryThingApiRepo,
) : ViewModel() {

    private var mutableNewsLiveData = MutableLiveData<NetworkResponse<News>>()

    /**
     * News live data
     */
    val newsLiveData = mutableNewsLiveData

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     */
    fun fetchNews(query: String, sortBy: String) {
        viewModelScope.launch {
            repoEveryThing.fetchNews(query, sortBy).collect {
                mutableNewsLiveData.value = it
            }
        }
    }
}
