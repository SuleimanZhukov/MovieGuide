package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.entities.Movie
import com.suleimanzhukov.movieguide.model.rest.MovieRepo

class RepositoryImpl : Repository {
    override fun getMovieById(id: String): Movie {
        val dto = MovieRepo.api.getMovieById(id).execute().body()
//        var postersDto = MovieRepo.api.getMoviePosterById(dto!!.id).execute().body()
//        if (postersDto!!.posters.size >= 2) {
//            var posterId = postersDto!!.posters[1].id
//            var posterPath = "https://imdb-api.com/posters/w500/$posterId"
//        } else {
//            var posterId = postersDto!!.posters[0].id
//            var posterPath = "https://imdb-api.com/posters/w500/$posterId"
//        }
        return Movie(dto!!.id, dto.title, dto.genres, dto.imDbRating, "")
    }

    override fun getNowPlayingMovies(): List<Movie> {
        val dto = MovieRepo.api.getNowPlayingMovies().execute().body()
        var nowPlayingMovies = mutableListOf<Movie>()
        val size = dto!!.items.size - 1
        for (i in 0..size) {
            var postersDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
            val postersSize = postersDto!!.posters.size - 1
            var posterPath = ""
            var posterId = ""
            for (j in 0..postersSize) {
                if (postersDto!!.posters[j].aspectRatio > 1.2) {
                    posterId = postersDto.posters[j].id
                    continue
                }
            }
            posterPath = "https://imdb-api.com/posters/w500/$posterId"
            nowPlayingMovies.add(
                Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres, dto.items[i].imDbRating, posterPath)
            )
        }
        return nowPlayingMovies
    }

    override fun getUpcomingMovies(): List<Movie> {
        val dto = MovieRepo.api.getUpcomingMovies().execute().body()
        var upcomingMovies = mutableListOf<Movie>()
        val size = dto!!.items.size - 1
        for (i in 0..size) {
//            var postersDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
//            var posterId = postersDto!!.posters[0].id
//            var posterPath = "https://imdb-api.com/posters/w500/$posterId"
            upcomingMovies.add(
                Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres, dto.items[i].imDbRating, "")
            )
        }
        return upcomingMovies
    }
}