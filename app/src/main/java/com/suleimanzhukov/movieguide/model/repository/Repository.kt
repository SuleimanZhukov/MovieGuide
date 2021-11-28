package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.entities.Movie

interface Repository {
    //Remote server
    fun getMovieById(id: String): Movie
    fun getNowPlayingMovies(): List<Movie>
    fun getUpcomingMovies(): List<Movie>

    //Database
    fun saveMovieToDB(movie: Movie)
    fun removeFromDB(id: String)
    fun getAllMoviesFromDB(): List<Movie>
    fun getMovieByIdFromDB(id: String): Movie
}