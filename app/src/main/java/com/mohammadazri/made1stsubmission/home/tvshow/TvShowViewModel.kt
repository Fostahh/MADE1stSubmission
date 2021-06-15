package com.mohammadazri.made1stsubmission.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.domain.usecase.TvShowUseCase

class TvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun getTvShows(page: Int): LiveData<Resource<List<TvShow>>> = tvShowUseCase.getTvShow(page).asLiveData()
}