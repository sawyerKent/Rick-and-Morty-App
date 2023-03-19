package com.skent.lib_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skent.lib_data.data.local.converters.DatabaseConverter
import com.skent.lib_data.data.local.dao.CharacterDao
import com.skent.lib_data.data.local.dao.EpisodeDao
import com.skent.lib_data.data.local.dao.LocationDao
import com.skent.lib_data.data.local.dao.RemoteKeyDao
import com.skent.lib_data.domain.models.character.RemoteKey
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.lib_data.domain.models.location.LocationEntity

@Database(entities = [
    Result::class,
    LocationEntity::class,
    EpisodeEntity::class,
    RemoteKey::class,
                     ],
    version = 7, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase(){
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getRemoteKeysDao(): RemoteKeyDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getEpisodeDao(): EpisodeDao
}