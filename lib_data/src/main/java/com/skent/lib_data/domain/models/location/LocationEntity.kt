package com.skent.lib_data.domain.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location_table")
data class LocationEntity(
    @SerializedName("created")
    val created: String?,
    @SerializedName("dimension")
    val dimension: String?,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("residents")
    val residents: List<String>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)

