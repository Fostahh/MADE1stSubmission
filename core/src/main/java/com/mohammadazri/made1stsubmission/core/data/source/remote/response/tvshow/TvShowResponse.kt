package com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val title: String?,
    @SerializedName("first_air_date") val releaseDate: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("vote_average") val rating: Double?,
    @SerializedName("poster_path") val image: String?
)
