package com.example.feature_keyword.repository

import com.example.core.database.dao.KeywordDao
import com.example.core.database.model.Keyword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** 検索履歴のリポジトリ */
interface KeyWordHistoryRepository {
    /** 検索履歴を保存する */
    suspend fun saveHistoryItem(input: String)

    /** 検索履歴を取得する */
    fun getHistoryList(): Flow<List<String>>

    /** 検索履歴を削除する */
    suspend fun clearHistory()
}

/**
 *  KeyWordHistoryRepositoryの実装クラス
 * @param keywordDao キーワードのDao
 */
class KeyWordHistoryRepositoryImpl
@Inject
constructor(
    private val keywordDao: KeywordDao
) : KeyWordHistoryRepository {
    /**
     * 直近の5件の入力されたキーワードを保存する
     * @param input 入力されたキーワード
     */
    override suspend fun saveHistoryItem(input: String) {
        if (input.isEmpty() || input.isBlank()) {
            return
        }
        val existingKeywords = keywordDao.findKeyword(input)

        if (existingKeywords.isNotEmpty()) {
            existingKeywords.forEach { keywordDao.deleteKeywordById(it.id) }
        }
        keywordDao.insertKeyword(Keyword(word = input))

        // 5件を超えた古い履歴を削除
        keywordDao.deleteOldKeywords()
    }

    /**
     * 検索履歴を取得する
     * @return 検索履歴のFlow
     */
    override fun getHistoryList(): Flow<List<String>> {
      return keywordDao.getKeywords().map { it.map { keyword -> keyword.word }.reversed() }
            .flowOn(Dispatchers.IO)
    }

    /** 検索履歴をクリアする */
    override suspend fun clearHistory() {
        keywordDao.deleteAllKeywords()
    }
}
