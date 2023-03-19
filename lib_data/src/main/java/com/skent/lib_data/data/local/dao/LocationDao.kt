package com.skent.lib_data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skent.lib_data.domain.models.location.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM location_table WHERE id=:id")
    suspend fun getSelectedLocation(id: Int): LocationEntity?

    @Query("SELECT id FROM location_table WHERE name=:name")
    suspend fun getSelectedLocationByName(name: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocationData(locationList: List<LocationEntity>)

    @Query("DELETE FROM location_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM location_table")
    suspend fun getNumberOfLocations() : Int
}