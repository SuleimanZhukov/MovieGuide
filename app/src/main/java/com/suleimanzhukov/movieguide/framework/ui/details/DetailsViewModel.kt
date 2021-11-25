package com.suleimanzhukov.movieguide.framework.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.framework.App
import com.suleimanzhukov.movieguide.model.repository.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: RepositoryImpl
) : ViewModel(), CoroutineScope by MainScope() {

    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getDetailsLiveData() = detailsLiveData

    fun getDetailsData() = getDataFromServer()

    private fun getDataFromServer() {
        detailsLiveData.value = AppState.Loading
        launch(Dispatchers.IO) {
            detailsLiveData.postValue(AppState.SuccessOneMovie(repository.getMovieById("")))
        }
    }

}