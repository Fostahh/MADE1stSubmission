package com.mohammadazri.made1stsubmission.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovieById(id: Int): LiveData<Resource<Movie>> = movieUseCase.getMovieById(id).asLiveData()

    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}