package com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowBodyResponse(
    @SerializedName("results")
    val results: List<TvShowResponse>
)