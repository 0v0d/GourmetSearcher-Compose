package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.model.Keyword
import kotlinx.coroutines.flow.Flow

/** 検索キーワード履歴へのアクセスを提供するDAO */
@Dao
interface KeywordDao {
    /** 保存された全キーワードを新しい順に取得 */
    @Query("SELECT * FROM keyword ORDER BY id DESC")
    fun getKeywords(): Flow<List<Keyword>>

    /** 特定のキーワードを検索 */
    @Query("SELECT * FROM keyword WHERE word = :input ORDER BY id ASC")
    suspend fun findKeyword(input: String): List<Keyword>

    @Insert
    suspend fun insertKeyword(keyword: Keyword): Long

    @Query("DELETE FROM keyword WHERE id = :id")
    suspend fun deleteKeywordById(id: Long)

    /** 最新5件以外の古いキーワードを削除 */
    @Query("DELETE FROM keyword WHERE id NOT IN (SELECT id FROM keyword ORDER BY id DESC LIMIT 5)")
    suspend fun deleteOldKeywords()

    @Query("DELETE FROM keyword")
    suspend fun deleteAllKeywords()
}