package com.suleimanzhukov.movieguide.model.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suleimanzhukov.movieguide.framework.App

@Database(entities = [WishlistEntity::class], version = 1, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao

    companion object {
        private const val NAME_DB = "WISHLIST_DB"
        val db: WishlistDatabase by lazy {
            Room.databaseBuilder(
                App.appContext,
                WishlistDatabase::class.java,
                NAME_DB
            ).build()
        }
    }
}