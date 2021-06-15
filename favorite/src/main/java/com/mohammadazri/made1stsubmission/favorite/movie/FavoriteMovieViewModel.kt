package com.mohammadazri.made1stsubmission.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getFavoriteMovie(): LiveData<List<Movie>> = movieUseCase.getFavoriteMovie().asLiveData()
}