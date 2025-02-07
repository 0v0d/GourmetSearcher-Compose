package com.example.core.cache.manager

import android.util.LruCache
import com.example.core.api.model.client.RestaurantList
import com.example.core.api.model.data.SearchKey
import retrofit2.Response

/**
 * キャッシュマネージャ
 * @param cache キャッシュ
 */
class CacheManager(
    private val cache: LruCache<String, Response<RestaurantList>?>,
) {
    /**
     * キャッシュから結果を取得する
     * @param searchKey 検索条件
     * @return レストラン情報 or null
     */
    fun get(searchKey: SearchKey): Response<RestaurantList>? {
        val cacheKey = searchKey.generateCacheKey()
        return cache[cacheKey]
    }

    /**
     * キャッシュに結果を保存する
     * @param searchKey 検索条件
     * @param response レストラン情報
     */
    fun put(
        searchKey: SearchKey,
        response: Response<RestaurantList>?,
    ) {
        val cacheKey = searchKey.generateCacheKey()
        cache.put(cacheKey, response)
    }
}
