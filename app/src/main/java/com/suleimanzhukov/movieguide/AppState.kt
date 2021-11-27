package com.suleimanzhukov.movieguide

import com.suleimanzhukov.movieguide.model.entities.Movie

sealed class AppState {
    data class SuccessNowPlaying(val nowPlayingMovies: List<Movie>) : AppState()
    data class SuccessUpcoming(val upcomingMovies: List<Movie>) : AppState()
    data class SuccessOneMovie(val movie: Movie) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}