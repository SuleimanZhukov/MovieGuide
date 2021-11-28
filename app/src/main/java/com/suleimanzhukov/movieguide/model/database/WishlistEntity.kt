package com.suleimanzhukov.movieguide.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val movieId: String,
    val movieTitle: String,
    val movieGenre: String,
    val movieRating: String,
    val movieOverview: String,
    val moviePoster: String,
    val wishlist: Boolean
)