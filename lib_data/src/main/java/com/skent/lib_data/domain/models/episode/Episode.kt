package com.skent.lib_data.domain.models.episode

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val episodeResults: List<EpisodeEntity>
)
