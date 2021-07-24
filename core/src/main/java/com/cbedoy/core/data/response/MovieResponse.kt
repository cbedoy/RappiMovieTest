package com.cbedoy.core.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val page: Int = 0,
    val results: List<MovieResult> = emptyList()
)

@Serializable
data class MovieResult(
    val adult: Boolean = false,
    val title: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val id: Long = 0,
    val popularity: Float = 0F,
    @SerialName(value = "release_date") val releaseDate: String?= "",
    @SerialName(value = "poster_path") val posterPath: String?= ""
)