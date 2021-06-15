package com.mohammadazri.made1stsubmission.core.data

import com.mohammadazri.made1stsubmission.core.data.source.local.LocalDataSource
import com.mohammadazri.made1stsubmission.core.data.source.remote.RemoteDataSource
import com.mohammadazri.made1stsubmission.core.data.source.remote.network.ApiResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.domain.repository.IMovieRepository
import com.mohammadazri.made1stsubmission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getMovies(page: Int): Flow<Resource<List<Movie>>> =
        object : com.mohammadazri.made1stsubmission.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie(page)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(listMovie)
            }

        }.asFlow()


    override fun getMovieById(id: Int): Flow<Resource<Movie>> =
        object : com.mohammadazri.made1stsubmission.core.data.NetworkBoundResource<Movie, DetailMovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id).map {
                    DataMapper.mapDetailMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.genres == null || data.runtime == null

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val detailMovie = DataMapper.mapDetailMovieResponseToEntities(data)
                localDataSource.insertDetailMovie(detailMovie)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> = localDataSource.getFavoritedMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }


    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapMovieDomaintoEntity(movie)
        executorService.execute {
            localDataSource.setFavoriteMovie(movieEntity, newState)
        }
    }
}
