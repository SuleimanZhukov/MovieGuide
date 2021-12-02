package com.suleimanzhukov.movieguide

import com.suleimanzhukov.movieguide.model.entities.Movie

sealed class AppState {
    //From server
    data class SuccessNowPlaying(val nowPlayingMovies: List<Movie>) : AppState()
    data class SuccessUpcoming(val upcomingMovies: List<Movie>) : AppState()
    data class SuccessOneMovie(val movie: Movie) : AppState()

    //From database
    data class SuccessWishlist(val wishlistMovies: List<Movie>) : AppState()
    data class SuccessSaveToWishlist(val wishlistMovie: Movie) : AppState()
    data class SuccessRemoveFromWishlist(val wishlistMovie: Movie) : AppState()
    data class SuccessGetMovieFromWishlist(val wishlistMovie: Movie) : AppState()

    //Search
    data class SuccessSearch(val searchMovies: List<Movie>) : AppState()

    //Error
    data class Error(val error: Throwable) : AppState()

    //Loading data
    object Loading : AppState()
}