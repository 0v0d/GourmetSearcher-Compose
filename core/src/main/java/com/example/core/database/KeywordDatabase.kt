package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.KeywordDao
import com.example.core.database.model.Keyword

/** 検索キーワード履歴を管理するデータベース */
@Database(
    entities = [Keyword::class],
    version = 1,
    exportSchema = false
)
abstract class KeywordDatabase : RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}
