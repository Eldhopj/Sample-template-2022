package com.eldhopj.myapplication.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eldhopj.myapplication.domain.RepoInterfaces.EveryThingApiRepo
import com.eldhopj.myapplication.domain.mapper.toMapper
import com.eldhopj.myapplication.domain.model.handlers.Output
import com.eldhopj.myapplication.domain.model.mapper.News
import com.eldhopj.myapplication.utils.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
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

    private var mutableNewsLiveDataResponse = MutableLiveData<News>()

    /**
     * News live data
     */
    val newsLiveData: LiveData<News> = mutableNewsLiveDataResponse

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
                    is Output.Loading -> setLoading(response.isLoading)
                    is Output.Success -> response.data?.let {
                        mutableNewsLiveDataResponse.value = it.toMapper()
                    }
                    is Output.Error -> handleError(response.errorData)
                    is Output.Exception -> handleException(response.throwable)
                }
            }
        }
    }
}
