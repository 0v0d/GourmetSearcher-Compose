package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.model.Keyword
import kotlinx.coroutines.flow.Flow

@Dao
interface KeywordDao {
    @Query("SELECT * FROM keyword ORDER BY id DESC")
    fun getKeywords(): Flow<List<Keyword>>

    @Query("SELECT * FROM keyword WHERE word = :input ORDER BY id ASC")
    suspend fun findKeyword(input: String): List<Keyword>

    @Insert
    suspend fun insertKeyword(keyword: Keyword): Long

    @Query("DELETE FROM keyword WHERE id = :id")
    suspend fun deleteKeywordById(id: Long)

    @Query("DELETE FROM keyword WHERE id NOT IN (SELECT id FROM keyword ORDER BY id DESC LIMIT 5)")
    suspend fun deleteOldKeywords()

    @Query("DELETE FROM keyword")
    suspend fun deleteAllKeywords()
}