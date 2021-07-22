package com.cbedoy.core.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbedoy.core.data.database.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie order by favorite = 0, popularity desc")
    fun getAllAsPaged(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie where favorite = 1 order by popularity desc")
    fun getFavoritesAsPaged(): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun modifyMovie(movie: Movie)
}