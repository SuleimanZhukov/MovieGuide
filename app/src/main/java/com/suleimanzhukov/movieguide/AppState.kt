package com.suleimanzhukov.movieguide

import com.suleimanzhukov.movieguide.model.entities.Movie

sealed class AppState {
    data class Success(val movies: List<Movie>) : AppState()
    data class SuccessOneMovie(val movie: Movie) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}