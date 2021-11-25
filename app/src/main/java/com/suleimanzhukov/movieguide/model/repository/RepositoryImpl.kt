package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.entities.Movie

class RepositoryImpl : Repository {
    override fun getMovieById(id: String): Movie {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getUpcomingMovies(): List<Movie> {
        TODO("Not yet implemented")
    }
}