package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.entities.Movie
import com.suleimanzhukov.movieguide.model.rest.MovieRepo

class RepositoryImpl : Repository {
    override fun getMovieById(id: String): Movie {
        val dto = MovieRepo.api.getMovieById(id).execute().body()
        var postersDto = MovieRepo.api.getMoviePosterById(dto!!.id).execute().body()
        var posterId = postersDto!!.posters.id
        var posterPath = "https://imdb-api.com/en/API/Posters/k_qnnx4mmj/$posterId"
        return Movie(dto.id, dto.title, dto.genres, dto.imDbRating, posterPath)
    }

    override fun getNowPlayingMovies(): List<Movie> {
        val dto = MovieRepo.api.getNowPlayingMovies().execute().body()
        var nowPlayingMovies = mutableListOf<Movie>()
        for (i in 0..dto!!.items.size) {
            var postersDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
            var posterId = postersDto!!.posters.id
            var posterPath = "https://imdb-api.com/en/API/Posters/k_qnnx4mmj/$posterId"
            nowPlayingMovies.add(
                Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres, dto.items[i].imDbRating, posterPath)
            )
        }
        return nowPlayingMovies
    }

    override fun getUpcomingMovies(): List<Movie> {
        val dto = MovieRepo.api.getUpcomingMovies().execute().body()
        var upcomingMovies = mutableListOf<Movie>()
        for (i in 0..dto!!.items.size) {
            var postersDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
            var posterId = postersDto!!.posters.id
            var posterPath = "https://imdb-api.com/en/API/Posters/k_qnnx4mmj/$posterId"
            upcomingMovies.add(
                Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres, dto.items[i].imDbRating, posterPath)
            )
        }
        return upcomingMovies
    }
}