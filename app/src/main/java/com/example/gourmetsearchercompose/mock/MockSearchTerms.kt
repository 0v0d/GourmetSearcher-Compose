package com.example.gourmetsearchercompose.mock

import com.example.gourmetsearchercompose.mock.MockSearchTerms.KEYWORD
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleHistoryList
import com.example.gourmetsearchercompose.model.data.CurrentLocation
import com.example.gourmetsearchercompose.model.data.SearchTerms
import kotlinx.collections.immutable.toImmutableList

/**
 * モック検索条件
 * @property sampleHistoryList キーワード履歴
 * @property KEYWORD キーワード
 */
object MockSearchTerms {
    val sampleHistoryList = listOf("item1", "item2", "item3").toImmutableList()
    const val KEYWORD = "焼肉"

    val sampleSearchTerms = SearchTerms(
        "keyword",
        CurrentLocation(35.0, 139.0),
        1
    )
}
