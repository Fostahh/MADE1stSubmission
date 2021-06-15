package com.mohammadazri.made1stsubmission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val overview: String?,
    val rating: Double?,
    val image: String?,
    val genres: String?,
    val episodes: Int?,
    val seasons: Int?,
    val isFavorite: Boolean?
) : Parcelable
