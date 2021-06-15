package com.mohammadazri.made1stsubmission.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.domain.usecase.TvShowUseCase

class FavoriteTvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<List<TvShow>> = tvShowUseCase.getFavoritedTvShow().asLiveData()
}