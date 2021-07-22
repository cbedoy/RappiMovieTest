package com.cbedoy.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}