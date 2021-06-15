package com.mohammadazri.made1stsubmission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val title: String?,
    val rating: Double?,
    val releaseDate: String?,
    val overview: String?,
    val image: String?,
    val genres: String?,
    val runtime: Int?,
    val isFavorite: Boolean?
) : Parcelable