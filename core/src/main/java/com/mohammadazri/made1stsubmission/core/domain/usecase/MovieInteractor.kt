package com.mohammadazri.made1stsubmission.core.domain.usecase

import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getMovies(page: Int): Flow<Resource<List<Movie>>> = movieRepository.getMovies(page)
    override fun getMovieById(id: Int): Flow<Resource<Movie>> = movieRepository.getMovieById(id)
    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()
    override fun setFavoriteMovie(movie: Movie, newState: Boolean) = movieRepository.setFavoriteMovie(movie, newState)
}