package com.suleimanzhukov.movieguide.model.rest

import com.suleimanzhukov.movieguide.model.restentities.Items
import com.suleimanzhukov.movieguide.model.restentities.MovieDTO
import com.suleimanzhukov.movieguide.model.restentities.PosterDTO
import retrofit2.Call
import retrofit2.http.*

interface MovieApi {
    @GET("Title/k_qnnx4mmj")
    fun getMovieById(
        @Query("") id: String
    ): Call<Items>

    @GET("Posters/{apiKey}/{id}")
    fun getMoviePosterById(
        @Query("apiKey") apiKey: String,
        @Query("id") id: String
    ): Call<PosterDTO>

    @GET("InTheaters/k_qnnx4mmj")
    fun getNowPlayingMovies(): Call<MovieDTO>

    @GET("ComingSoon/k_qnnx4mmj")
    fun getUpcomingMovies(): Call<MovieDTO>
}