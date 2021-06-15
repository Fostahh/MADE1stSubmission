package com.mohammadazri.made1stsubmission.core.data.source.local

import com.mohammadazri.made1stsubmission.core.data.source.local.entity.MovieEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.TvShowEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.room.MovieTvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieTvShowDao: MovieTvShowDao) :
    ILocalDataSource {

    override suspend fun insertMovies(movies: List<MovieEntity>) =
        movieTvShowDao.insertMovies(movies)

    override fun getMovies(): Flow<List<MovieEntity>> =
        movieTvShowDao.getMovies()

    override suspend fun insertDetailMovie(movie: MovieEntity) =
        movieTvShowDao.insertDetailMovie(movie)

    override fun getMovieById(id: Int): Flow<MovieEntity> =
        movieTvShowDao.getMovieById(id)

    override fun getFavoritedMovie(): Flow<List<MovieEntity>> =
        movieTvShowDao.getFavoritedMovie()

    override fun setFavoriteMovie(
        movie: MovieEntity,
        newState: Boolean,
    ) {
        movie.isFavorite = newState
        movieTvShowDao.setFavoriteMovie(movie)
    }

    override suspend fun insertTvShows(tvShows: List<TvShowEntity>) =
        movieTvShowDao.insertTvShows(tvShows)

    override fun getTvShows(): Flow<List<TvShowEntity>> =
        movieTvShowDao.getTvShows()

    override suspend fun insertDetailTvShow(tvShow: TvShowEntity) =
        movieTvShowDao.insertDetailTvShow(tvShow)

    override fun getTvShowById(id: Int): Flow<TvShowEntity> =
        movieTvShowDao.getTvShowById(id)

    override fun getFavoritedTvShow(): Flow<List<TvShowEntity>> =
        movieTvShowDao.getFavoritedTvShow()

    override fun setFavoriteTvShow(
        tvShow: TvShowEntity,
        newState: Boolean,
    ) {
        tvShow.isFavorite = newState
        movieTvShowDao.setFavoriteTvShow(tvShow)
    }
}