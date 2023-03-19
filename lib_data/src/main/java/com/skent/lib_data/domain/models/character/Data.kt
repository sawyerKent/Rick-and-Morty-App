package com.skent.lib_data.domain.models.character

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>?
)