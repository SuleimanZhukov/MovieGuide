package com.suleimanzhukov.movieguide.model.restentities

import com.google.gson.annotations.SerializedName

class PosterDTO(
    @SerializedName("posters")
    var posters: List<Poster>,

    @SerializedName("backdrops")
    var backdrops: List<Backdrop>
)