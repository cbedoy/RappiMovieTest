package com.cbedoy.core.data

import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResult

fun MovieResult.toMovie(): Movie {
    return Movie(
        id,
        image = "https://image.tmdb.org/t/p/w440_and_h660_face/$posterPath",
        title,
        overview,
        popularity
    )
}