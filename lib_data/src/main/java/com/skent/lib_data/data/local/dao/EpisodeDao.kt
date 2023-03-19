package com.skent.lib_data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skent.lib_data.domain.models.episode.EpisodeEntity

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episode_table WHERE id=:id")
    suspend fun getSelectedEpisode(id: Int): EpisodeEntity?

    @Query("SELECT id FROM episode_table WHERE name=:name")
    suspend fun getSelectedEpisodeByName(name: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodeData(episodeList: List<EpisodeEntity>)

    @Query("DELETE FROM episode_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM episode_table")
    suspend fun getNumberOfEpisodes() : Int
}