package com.cbedoy.core.data.service

import com.cbedoy.core.data.response.MovieResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "2754de7d0f03edc5c2d26999716f731f",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : NetworkResponse<MovieResponse, Void>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "2754de7d0f03edc5c2d26999716f731f",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ) : NetworkResponse<MovieResponse, Void>
}