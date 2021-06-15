package com.mohammadazri.made1stsubmission.core.data.source.remote

import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    suspend fun getMovie(page: Int): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>>
    suspend fun getTvShow(page: Int): Flow<ApiResponse<List<TvShowResponse>>>
    suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<DetailTvShowResponse>>
}