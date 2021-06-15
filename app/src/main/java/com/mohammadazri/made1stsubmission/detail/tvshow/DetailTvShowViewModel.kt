package com.mohammadazri.made1stsubmission.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.domain.usecase.TvShowUseCase

class DetailTvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun getTvShowById(id: Int): LiveData<Resource<TvShow>> = tvShowUseCase.getTvShowById(id).asLiveData()
    fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean) =
        tvShowUseCase.setFavoriteMovie(tvShow, newState)
}