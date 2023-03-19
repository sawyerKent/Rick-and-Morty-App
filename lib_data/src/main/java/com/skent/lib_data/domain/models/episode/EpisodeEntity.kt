package com.skent.lib_data.domain.models.episode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episode_table")
data class EpisodeEntity(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("characters")
    val characters: List<String>?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)