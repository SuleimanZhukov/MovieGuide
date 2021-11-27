package com.suleimanzhukov.movieguide.model.rest

import com.suleimanzhukov.movieguide.model.restentities.Items
import com.suleimanzhukov.movieguide.model.restentities.MovieDTO
import com.suleimanzhukov.movieguide.model.restentities.PosterDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("Title/k_qnnx4mmj")
    fun getMovieById(
        @Query("") id: String
    ): Call<Items>

    @GET("Posters/k_qnnx4mmj/tt2953050")
    fun getMoviePosterById(
        @Query("") id: String
    ): Call<PosterDTO>

    @GET("InTheaters/k_qnnx4mmj")
    fun getNowPlayingMovies(): Call<MovieDTO>

    @GET("ComingSoon/k_qnnx4mmj")
    fun getUpcomingMovies(): Call<MovieDTO>
}