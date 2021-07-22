package com.cbedoy.core.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val page: Int = 0,
    val results: List<MovieResult> = emptyList()
)

@JsonClass(generateAdapter = true)
data class MovieResult(
    val adult: Boolean = false,
    val title: String = "",
    val overview: String = "",
    val id: Long = 0,
    val popularity: Float = 0F,
    @Json(name = "release_date") val releaseDate: String?= "",
    @Json(name = "poster_path") val posterPath: String?= ""
)