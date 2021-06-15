package com.mohammadazri.made1stsubmission.core.data

import com.mohammadazri.made1stsubmission.core.data.source.local.LocalDataSource
import com.mohammadazri.made1stsubmission.core.data.source.remote.RemoteDataSource
import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.TvShowResponse
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.domain.repository.ITvShowRepository
import com.mohammadazri.made1stsubmission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ITvShowRepository {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getTvShow(page: Int): Flow<Resource<List<TvShow>>> =
        object : com.mohammadazri.made1stsubmission.core.data.NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShows().map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow(page)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShows(listTvShow)
            }

        }.asFlow()


    override fun getTvShowById(id: Int): Flow<Resource<TvShow>> =
        object : com.mohammadazri.made1stsubmission.core.data.NetworkBoundResource<TvShow, DetailTvShowResponse>() {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getTvShowById(id).map {
                    DataMapper.mapDetailTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.genres == null || data.episodes == null || data.seasons == null

            override suspend fun createCall(): Flow<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override suspend fun saveCallResult(data: DetailTvShowResponse) {
                val detailTvShow = DataMapper.mapDetailTvShowResponseToEntities(data)
                localDataSource.insertDetailTvShow(detailTvShow)
            }

        }.asFlow()

    override fun getFavoritedTvShow(): Flow<List<TvShow>> =
        localDataSource.getFavoritedTvShow().map {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(tvShow: TvShow, newState: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomaintoEntity(tvShow)
        executorService.execute {
            localDataSource.setFavoriteTvShow(tvShowEntity, newState)
        }
    }
}
