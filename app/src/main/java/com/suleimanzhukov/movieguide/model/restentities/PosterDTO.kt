package com.suleimanzhukov.movieguide.model.restentities

import com.google.gson.annotations.SerializedName

class PosterDTO(
    @SerializedName("posters")
    var posters: Poster
)