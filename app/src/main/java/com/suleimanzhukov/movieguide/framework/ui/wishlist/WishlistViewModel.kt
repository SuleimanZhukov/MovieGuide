package com.suleimanzhukov.movieguide.framework.ui.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.model.entities.Movie
import com.suleimanzhukov.movieguide.model.repository.Repository
import kotlinx.coroutines.*

class WishlistViewModel(
    private val repository: Repository
) : ViewModel() {

    private val wishlistLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getWishlistLiveData() = wishlistLiveData

    fun getMoviesFromDB() = getDataFromDB()

    private fun getDataFromDB() {
        wishlistLiveData.value = AppState.Loading
        Thread {
            wishlistLiveData.postValue(AppState.SuccessWishlist(repository.getAllMoviesFromDB()))
        }.start()
    }
}