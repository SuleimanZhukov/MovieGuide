package com.suleimanzhukov.movieguide.framework.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.model.repository.Repository

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val searchMovieLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getSearchLiveData() = searchMovieLiveData

    fun getMovies(title: String) = getSearchMovies(title)

    private fun getSearchMovies(title: String) {
        searchMovieLiveData.value = AppState.Loading
        Thread {
            searchMovieLiveData.postValue(AppState.SuccessSearch(repository.searchForMovies(title)))
        }.start()
    }
}