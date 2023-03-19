package com.skent.lib_data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skent.lib_data.domain.models.character.RemoteKey

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKey>)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()
}
