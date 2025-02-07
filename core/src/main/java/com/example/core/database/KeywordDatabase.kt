package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.KeywordDao
import com.example.core.database.model.Keyword

@Database(
    entities = [Keyword::class],
    version = 1,
    exportSchema = false
)
abstract class KeywordDatabase : RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}


