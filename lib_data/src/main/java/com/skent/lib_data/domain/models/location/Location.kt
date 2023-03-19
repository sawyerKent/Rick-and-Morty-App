package com.skent.lib_data.domain.models.location

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val locationResults: List<LocationEntity>
)

