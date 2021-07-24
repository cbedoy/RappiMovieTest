package com.cbedoy.core.data.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Movie(
    @PrimaryKey val uuid: Long = 0,
    val image: String = "",
    val title: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    val rated: Float = 0F
) : Parcelable