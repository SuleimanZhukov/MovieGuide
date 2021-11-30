package com.suleimanzhukov.movieguide.framework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val mainLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMainLiveData() = mainLiveData

    fun getMainData() = getDataFromServer()

    private fun getDataFromServer() {
        mainLiveData.value = AppState.Loading
        Thread {
            mainLiveData.postValue(AppState.SuccessNowPlaying(repository.getNowPlayingMovies()))
            mainLiveData.postValue(AppState.SuccessUpcoming(repository.getUpcomingMovies()))
        }.start()
    }
}