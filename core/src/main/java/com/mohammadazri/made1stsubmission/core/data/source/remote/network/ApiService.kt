package com.mohammadazri.made1stsubmission.core.data.source.remote.network

import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.MovieBodyResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.TvShowBodyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieBodyResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): DetailMovieResponse

    @GET("tv/top_rated")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): TvShowBodyResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ) : DetailTvShowResponse
}