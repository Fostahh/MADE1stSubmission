package com.mohammadazri.made1stsubmission.core.data.source.remote

import com.mohammadazri.made1stsubmission.core.BuildConfig
import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiService
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) :
    IRemoteDataSource {

    override suspend fun getMovie(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            val response = apiService.getMovie(BuildConfig.APIKey, page)
            val listMovie = response.results
            try {
                if (listMovie.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTvShow(page: Int): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            val response = apiService.getTvShow(BuildConfig.APIKey, page)
            val listTvShow = response.results
            try {
                if (listTvShow.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            val response = apiService.getDetailMovie(id, BuildConfig.APIKey)
            try {
                if (response.id != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<DetailTvShowResponse>> {
        return flow {
            val response = apiService.getDetailTvShow(id, BuildConfig.APIKey)
            try {
                if (response.id != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}