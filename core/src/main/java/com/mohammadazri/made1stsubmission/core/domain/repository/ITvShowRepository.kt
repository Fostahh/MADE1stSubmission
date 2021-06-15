package com.mohammadazri.made1stsubmission.core.domain.repository

import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getTvShow(page: Int): Flow<Resource<List<TvShow>>>
    fun getTvShowById(id: Int): Flow<Resource<TvShow>>
    fun getFavoritedTvShow(): Flow<List<TvShow>>
    fun setFavoriteMovie(tvShow: TvShow, newState: Boolean)
}