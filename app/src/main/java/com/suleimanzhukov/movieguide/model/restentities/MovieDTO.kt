package com.suleimanzhukov.movieguide.model.restentities

import com.google.gson.annotations.SerializedName

class MovieDTO(
    @SerializedName("items")
    var items: List<Items>
)