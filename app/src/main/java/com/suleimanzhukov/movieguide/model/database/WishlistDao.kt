package com.suleimanzhukov.movieguide.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WishlistDao {
    @Query("SELECT * FROM WishlistEntity")
    fun getAllMovies(): List<WishlistEntity>

    @Query("SELECT * FROM WishlistEntity WHERE movieId = :id")
    fun getMovieById(id: String): WishlistEntity

    @Insert
    fun insert(entity: WishlistEntity)

    @Query("DELETE FROM WishlistEntity WHERE movieId = :id")
    fun delete(id: String)
}