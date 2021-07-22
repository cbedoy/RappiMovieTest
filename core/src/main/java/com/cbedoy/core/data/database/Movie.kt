package com.cbedoy.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val uuid: String = "",
    val image: String = "",
    val title: String = "",
    val favorite: Boolean = false,
    val popularity: Float = 0F
)