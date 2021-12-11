package com.suleimanzhukov.movieguide.model.repository

import com.suleimanzhukov.movieguide.model.database.WishlistDatabase
import com.suleimanzhukov.movieguide.model.database.WishlistEntity
import com.suleimanzhukov.movieguide.model.entities.Movie
import com.suleimanzhukov.movieguide.model.rest.MovieRepo

class RepositoryImpl : Repository {
    override fun getMovieById(id: String): Movie {
        val dto = MovieRepo.api.getMovieById(id).execute().body()
        var postersDto = MovieRepo.api.getMoviePosterById(dto!!.id).execute().body()
        var posterPath = ""
        if (postersDto!!.posters.size >= 1) {
            if (postersDto.posters.size >= 2) {
                var posterId = postersDto.posters[1].id
                posterPath = "https://imdb-api.com/posters/w500/$posterId"
            } else {
                var posterId = postersDto.posters[0].id
                posterPath = "https://imdb-api.com/posters/w500/$posterId"
            }
        }
        return Movie(dto!!.id, dto.title, dto.genres, dto.imDbRating, dto.overview ?: "", posterPath, false)
    }

    override fun getNowPlayingMovies(): List<Movie> {
        val dto = MovieRepo.api.getNowPlayingMovies().execute().body()
        var nowPlayingMovies = mutableListOf<Movie>()
        val size = dto!!.items.size - 1
        for (i in 0..size) {
            var imagePath = ""
            var imageDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
            if (imageDto!!.posters.size >= 1) {
                var imageId = imageDto.posters[0].id
                imagePath = "https://imdb-api.com/posters/w300/$imageId"
            }
            nowPlayingMovies.add(
                Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres,
                    dto.items[i].imDbRating, "", imagePath, false)
            )
        }
        return nowPlayingMovies
    }

    override fun getUpcomingMovies(): List<Movie> {
        val dto = MovieRepo.api.getUpcomingMovies().execute().body()
        var upcomingMovies = mutableListOf<Movie>()
        val size = dto!!.items.size - 1
        if (dto.items.size >= 1) {
            for (i in 0..size) {
                var posterPath = ""
                var postersDto = MovieRepo.api.getMoviePosterById(dto.items[i].id).execute().body()
                if (postersDto!!.posters.size >= 1) {
                    var posterId = postersDto.posters[0].id
                    posterPath = "https://imdb-api.com/posters/w300/$posterId"
                }
                upcomingMovies.add(
                    Movie(dto.items[i].id, dto.items[i].title, dto.items[i].genres,
                        dto.items[i].imDbRating, "", posterPath, false)
                )
            }
        }
        return upcomingMovies
    }

    override fun searchForMovies(title: String): List<Movie> {
        val dto = MovieRepo.api.searchForMovieByTitle(title).execute().body()
        var searchResultMovies = mutableListOf<Movie>()
        val size = dto!!.results.size - 1
        var count = 0
        if (dto.results.size >= 1) {
            for (i in 0..size) {
                var posterPath = ""
                var postersDto = MovieRepo.api.getMoviePosterById(dto.results[i].id).execute().body()
                if (postersDto!!.posters.size >= 1) {
                    var posterId = postersDto.posters[0].id
                    posterPath = "https://imdb-api.com/posters/w300/$posterId"
                }
                searchResultMovies.add(
                    Movie(dto.results[i].id, dto.results[i].title, "", "", "", posterPath, false)
                )
                count++
                if (count == 6) {
                    break
                }
            }
        }
        return searchResultMovies
    }

    override fun saveMovieToDB(movie: Movie): Movie {
        WishlistDatabase.db.wishlistDao().insert(convertMovieToWishlistEntity(movie))
        return movie
    }


    override fun removeFromDB(movie: Movie): Movie {
        WishlistDatabase.db.wishlistDao().delete(movie.id)
        return movie
    }

    override fun getAllMoviesFromDB(): List<Movie> =
        convertWishlistEntityToListOfMovies(WishlistDatabase.db.wishlistDao().getAllMovies())

    override fun getMovieByIdFromDB(id: String): Movie =
        convertEntityToMovie(WishlistDatabase.db.wishlistDao().getMovieById(id))

    private fun convertMovieToWishlistEntity(movie: Movie): WishlistEntity =
        WishlistEntity(
            0,
            movie.id,
            movie.title,
            movie.genre,
            movie.rating,
            movie.overview,
            movie.poster,
            movie.wishlist
        )

    private fun convertWishlistEntityToListOfMovies(entityList: List<WishlistEntity>): List<Movie> =
        entityList.map {
            Movie(it.movieId, it.movieTitle, it.movieGenre,
                it.movieRating, it.movieOverview, it.moviePoster, it.wishlist)
        }

    private fun convertEntityToMovie(entity: WishlistEntity): Movie =
        Movie(entity.movieId, entity.movieTitle, entity.movieGenre,
            entity.movieRating, entity.movieOverview, entity.moviePoster, entity.wishlist)

}