package com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.GenreResponse

data class DetailMovieResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val rating: Double?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val image: String?,

    @SerializedName("genres")
    val genres: List<GenreResponse>?,

    @SerializedName("runtime")
    val runtime: Int?
)