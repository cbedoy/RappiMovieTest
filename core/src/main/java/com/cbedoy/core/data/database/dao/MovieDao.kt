package com.cbedoy.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbedoy.core.data.database.models.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie order by popularity desc")
    suspend fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movie order by rated desc")
    suspend fun getTopRated(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun modifyMovie(movie: Movie)
}