package com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName


data class MovieBodyResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)