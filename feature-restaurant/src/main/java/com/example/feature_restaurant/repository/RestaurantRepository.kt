package com.example.feature_restaurant.repository

import com.example.core.api.model.client.RestaurantList
import com.example.feature_restaurant.model.SearchTerms
import com.example.feature_restaurant.source.CachedRestaurantDataSourceProxy
import retrofit2.Response
import javax.inject.Inject

/** ホットペッパーグルメAPIを利用して、レストラン情報を取得する */
interface RestaurantRepository {
    /**
     * リポジトリ情報を取得
     * @param searchTerms 検索条件
     * @return Response<RestaurantList>レストラン情報 or null
     */
    suspend fun searchRestaurantRepository(searchTerms: SearchTerms): Response<RestaurantList>?
}

/**
 * RestaurantRepositoryの実装クラス
 *  @param dataSource データソース
 */
class RestaurantRepositoryImpl @Inject constructor(
    private val dataSource: CachedRestaurantDataSourceProxy
) : RestaurantRepository {

    override suspend fun searchRestaurantRepository(searchTerms: SearchTerms): Response<RestaurantList>? {
        return dataSource.getRestaurants(searchTerms)
    }
}
