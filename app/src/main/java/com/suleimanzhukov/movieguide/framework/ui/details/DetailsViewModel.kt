package com.suleimanzhukov.movieguide.framework.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: Repository
) : ViewModel(), CoroutineScope by MainScope() {

    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getDetailsLiveData() = detailsLiveData

    fun getDetailsData(id: String) = getDataFromServer(id)

    private fun getDataFromServer(id: String) {
        detailsLiveData.value = AppState.Loading
        launch(Dispatchers.IO) {
            detailsLiveData.postValue(AppState.SuccessOneMovie(repository.getMovieById(id)))
        }
    }

}