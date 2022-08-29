package com.eldhopj.myapplication.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eldhopj.myapplication.data.remote.Result
import com.eldhopj.myapplication.data.repositories.EveryThingApiRepo
import com.eldhopj.myapplication.domain.model.News
import com.eldhopj.myapplication.utils.BaseViewModel
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
) : BaseViewModel() {

    private var mutableNewsLiveData = MutableLiveData<News>()

    /**
     * News live data
     */
    val newsLiveData: LiveData<News> = mutableNewsLiveData

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     */
    fun fetchNews(query: String, sortBy: String) {
        viewModelScope.launch {
            repoEveryThing.fetchNews(query, sortBy).collect { response ->
                when (response) {
                    is Result.Loading -> {
                        setLoading(response.isLoading)
                    }
                    is Result.Success -> {
                        response.data?.let {
                            mutableNewsLiveData.value = it
                        }
                    }
                    is Result.Error -> {
                        handleError(response.errorData)
                    }
                    is Result.Exception -> {
                        handleException(response.throwable)
                    }
                }
            }
        }
    }
}
