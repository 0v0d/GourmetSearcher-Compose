package com.example.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/** 検索履歴のキーワードを表すエンティティ */
@Entity
data class Keyword(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String
)