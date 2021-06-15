package com.mohammadazri.made1stsubmission.core.domain.usecase

import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow

class TvShowInteractor(private val tvShowRepository: ITvShowRepository): TvShowUseCase {
    override fun getTvShow(page: Int): Flow<Resource<List<TvShow>>> = tvShowRepository.getTvShow(page)
    override fun getTvShowById(id: Int): Flow<Resource<TvShow>> = tvShowRepository.getTvShowById(id)
    override fun getFavoritedTvShow(): Flow<List<TvShow>> = tvShowRepository.getFavoritedTvShow()
    override fun setFavoriteMovie(tvShow: TvShow, newState: Boolean) = tvShowRepository.setFavoriteMovie(tvShow, newState)
}