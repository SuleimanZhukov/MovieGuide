package com.suleimanzhukov.movieguide.model.rest

import com.suleimanzhukov.movieguide.model.restentities.Items
import com.suleimanzhukov.movieguide.model.restentities.MovieDTO
import com.suleimanzhukov.movieguide.model.restentities.PosterDTO
import com.suleimanzhukov.movieguide.model.restentities.SearchDTO
import retrofit2.Call
import retrofit2.http.*

interface MovieApi {
    @GET("Title/k_qnnx4mmj/{id}")
    fun getMovieById(
        @Path("id") id: String
    ): Call<Items>

    @GET("Posters/k_qnnx4mmj/{id}")
    fun getMoviePosterById(
        @Path("id") id: String
    ): Call<PosterDTO>

    @GET("InTheaters/k_qnnx4mmj")
    fun getNowPlayingMovies(): Call<MovieDTO>

    @GET("ComingSoon/k_qnnx4mmj")
    fun getUpcomingMovies(): Call<MovieDTO>

    @GET("Search/k_qnnx4mmj/{expression}")
    fun searchForMovieByTitle(
        @Path("expression") title: String
    ): Call<SearchDTO>
}