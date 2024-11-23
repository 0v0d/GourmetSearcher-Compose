package com.example.gourmetsearchercompose.mock

import kotlinx.collections.immutable.toImmutableList

/**
 * モック検索条件
 * @property keywordHistory キーワード履歴
 * @property KEYWORD キーワード
 */
object MockSearchTerms {
    val keywordHistory = listOf("焼肉", "寿司", "ラーメン").toImmutableList()
    const val KEYWORD = "焼肉"
}
