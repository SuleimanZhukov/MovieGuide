package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.entities.Movie

interface Repository {
    fun getMovieById(id: String): Movie
    fun getNowPlayingMovies(): List<Movie>
    fun getUpcomingMovies(): List<Movie>
}