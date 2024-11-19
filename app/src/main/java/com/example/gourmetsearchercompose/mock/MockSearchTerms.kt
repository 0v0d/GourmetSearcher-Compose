package com.example.gourmetsearchercompose.mock

import com.example.gourmetsearchercompose.model.data.CurrentLocation
import com.example.gourmetsearchercompose.model.data.SearchTerms

/**
 * モック検索条件
 * @property mockSearchTerms モック検索条件
 */
object MockSearchTerms {
    val mockSearchTerms = SearchTerms(
        "焼肉",
        CurrentLocation(34.67, 135.52),
        3
    )
    val keywordHistory = listOf("焼肉", "寿司", "ラーメン")
}