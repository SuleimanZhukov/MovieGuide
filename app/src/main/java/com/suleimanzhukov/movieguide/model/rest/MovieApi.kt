package com.suleimanzhukov.movieguide.model.rest

import com.suleimanzhukov.movieguide.model.restentities.Items
import com.suleimanzhukov.movieguide.model.restentities.MovieDTO
import com.suleimanzhukov.movieguide.model.restentities.PosterDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("Title/k_qnnx4mmj")
    fun getMovieById(
        @Query("") id: String
    ): Items

    @GET("Posters/k_qnnx4mmj")
    fun getMoviePosterById(
        @Query("") id: String
    ): PosterDTO

    @GET("InTheaters/k_qnnx4mmj")
    fun getNowPlayingMovies(): MovieDTO

    @GET("ComingSoon/k_qnnx4mmj")
    fun getUpcomingMovies(): MovieDTO
}