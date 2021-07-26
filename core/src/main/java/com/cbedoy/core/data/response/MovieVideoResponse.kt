package com.cbedoy.core.data.service

import kotlinx.serialization.Serializable

@Serializable
data class MovieVideoResponse(
    val results: List<MovieVideoResponseResult> = emptyList()
)

@Serializable
data class MovieVideoResponseResult(
        val id: String = "",
        val key: String = "",
        val name: String = "",
        val site: String = "",
        val type: String = ""
)