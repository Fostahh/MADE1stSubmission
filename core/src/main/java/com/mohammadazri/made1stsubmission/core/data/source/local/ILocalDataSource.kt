package com.mohammadazri.made1stsubmission.core.data.source.local

import com.mohammadazri.made1stsubmission.core.data.source.local.entity.MovieEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {

    suspend fun insertMovies(movies: List<MovieEntity>)

    fun getMovies(): Flow<List<MovieEntity>>

    suspend fun insertDetailMovie(movie: MovieEntity)

    fun getMovieById(id: Int): Flow<MovieEntity>

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)

    fun getFavoritedMovie(): Flow<List<MovieEntity>>

    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    fun getTvShows(): Flow<List<TvShowEntity>>

    suspend fun insertDetailTvShow(tvShow: TvShowEntity)

    fun getTvShowById(id: Int): Flow<TvShowEntity>

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean)

    fun getFavoritedTvShow(): Flow<List<TvShowEntity>>
}