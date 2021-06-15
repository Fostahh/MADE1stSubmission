package com.mohammadazri.made1stsubmission.core.domain.repository

import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(page: Int): Flow<Resource<List<Movie>>>
    fun getMovieById(id: Int): Flow<Resource<Movie>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}