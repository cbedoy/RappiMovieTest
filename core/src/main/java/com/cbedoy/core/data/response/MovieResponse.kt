package com.cbedoy.core.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val page: Int = 0,
    @Json(name = "total_results") val totalResults: Long = 0,
    @Json(name = "total_pages") val totalPages: Long = 0,
    val results: List<MovieResult> = emptyList()
)

@JsonClass(generateAdapter = true)
data class MovieResult(
    val title: String = "",
    val overview: String = "",
    val id: Long = 0,
    val popularity: Float = 0F,
    @Json(name = "poster_path") val posterPath: String?= ""
)