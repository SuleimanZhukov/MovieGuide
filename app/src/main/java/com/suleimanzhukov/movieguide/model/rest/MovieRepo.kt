package com.suleimanzhukov.movieguide.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepo {
    val api: MovieApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl("https://imdb-api.com/en/API/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        adapter.create(MovieApi::class.java)

    }
}