package com.example.feature_restaurant.mock

import com.example.core.model.data.CurrentLocation
import com.example.feature_restaurant.SearchTerms
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms

/**
 * モック検索条件
 * @property sampleSearchTerms 検索条件
 */
object MockSearchTerms {
    val sampleSearchTerms = SearchTerms(
        "keyword",
        CurrentLocation(35.0, 139.0),
        1
    )
    val sampleSearchKey = sampleSearchTerms.toSearchKey()

    val cacheKey = sampleSearchKey.generateCacheKey()
}
