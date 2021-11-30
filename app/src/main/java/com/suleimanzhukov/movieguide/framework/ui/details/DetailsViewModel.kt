package com.suleimanzhukov.movieguide.framework.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.model.entities.Movie
import com.suleimanzhukov.movieguide.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getDetailsLiveData() = detailsLiveData

    fun getDetailsData(id: String) = getDataFromServer(id)

    fun addMovieToWishlist(movie: Movie) = addToWishlist(movie)

    fun removeMovieFromWishlist(movie: Movie) = removeFromWishlist(movie)

    fun getMovieById(id: String) = getMovieByIdFromDB(id)

    private fun getMovieByIdFromDB(id: String) {
        Thread {
            detailsLiveData.postValue(AppState.SuccessGetMovieFromWishlist(repository.getMovieByIdFromDB(id)))
        }.start()
    }

    private fun removeFromWishlist(movie: Movie) {
        Thread {
            detailsLiveData.postValue(AppState.SuccessRemoveFromWishlist(repository.removeFromDB(movie)))
        }.start()
    }

    private fun addToWishlist(movie: Movie) {
        Thread {
            detailsLiveData.postValue(AppState.SuccessSaveToWishlist(repository.saveMovieToDB(movie)))
        }.start()
    }

    private fun getDataFromServer(id: String) {
        detailsLiveData.value = AppState.Loading
        Thread {
            detailsLiveData.postValue(AppState.SuccessOneMovie(repository.getMovieById(id)))
        }.start()
    }

}