package com.mohammadazri.made1stsubmission.core.data.source.local.room

import androidx.room.*
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.MovieEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM favoritemovie ORDER BY rating DESC")
    fun getMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movie: MovieEntity)

    @Query("SELECT * FROM favoritemovie where movieId = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Update
    fun setFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM favoritemovie where isFavorite = 1 ORDER BY rating DESC")
    fun getFavoritedMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM favoritetvshow ORDER BY rating DESC")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM favoritetvshow where tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): Flow<TvShowEntity>

    @Update
    fun setFavoriteTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM favoritetvshow where isFavorite = 1 ORDER BY rating DESC")
    fun getFavoritedTvShow(): Flow<List<TvShowEntity>>
}