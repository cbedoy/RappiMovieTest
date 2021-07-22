package com.cbedoy.core.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val uuid: Long = 0,
    val image: String = "",
    val title: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    val rated: Float = 0F
)