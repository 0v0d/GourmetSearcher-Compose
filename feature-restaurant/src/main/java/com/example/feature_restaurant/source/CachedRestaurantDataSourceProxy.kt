package com.example.feature_restaurant.source

import com.example.core.api.model.client.RestaurantList
import com.example.core.cache.manager.CacheManager
import com.example.feature_restaurant.model.SearchTerms
import retrofit2.Response
import javax.inject.Inject

/**
 * キャッシュを利用して、レストラン情報を取得するデータソース
 * @param remoteDataSource リモートデータソース
 * @param cacheManager キャッシュ管理
 */
class CachedRestaurantDataSourceProxy @Inject constructor(
    private val remoteDataSource: RemoteRestaurantDataSource,
    private val cacheManager: CacheManager
) : RestaurantDataSource {

    /**
     * レストラン情報を取得し、キャッシュに保存
     * @param searchTerms 検索条件
     * @return レストラン情報 or null
     */
    override suspend fun getRestaurants(
        searchTerms: SearchTerms
    ): Response<RestaurantList>? {
        val searchKey = searchTerms.toSearchKey()

        val cachedResult = cacheManager.get(searchKey)
        if (cachedResult != null) {
            return cachedResult
        }

        // キャッシュになければ、実際のデータソースから取得
        val result = remoteDataSource.getRestaurants(searchTerms)

        // 結果をキャッシュに保存
        if (result != null) {
            cacheManager.put(searchKey, result)
        }

        return result
    }
}