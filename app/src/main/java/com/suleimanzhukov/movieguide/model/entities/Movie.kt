package com.suleimanzhukov.movieguide.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val genre: String,
    val rating: String,
    val overview: String,
    val poster: String,
    val wishlist: Boolean
) : Parcelable