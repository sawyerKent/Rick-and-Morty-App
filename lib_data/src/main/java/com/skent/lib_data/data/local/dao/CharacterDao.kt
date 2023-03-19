package com.skent.lib_data.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skent.lib_data.domain.models.character.Result

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character_table")
    fun getAllCharacterData(): PagingSource<Int, Result>

    @Query("SELECT * FROM character_table WHERE id=:id")
    suspend fun getSelectedCharacter(id: Int): Result?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterData(characterResult: List<Result>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM character_table")
    suspend fun getCharDataSize() : Int

    @Query("SELECT * FROM character_table where id >= :lower AND id <= :upper")
    suspend fun getRangeOfCharacters(lower: Int, upper:Int) : List<Result>
}
