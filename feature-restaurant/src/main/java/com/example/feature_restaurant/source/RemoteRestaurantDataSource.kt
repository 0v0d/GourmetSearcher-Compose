package com.example.feature_restaurant.source

import com.example.core.api.model.client.RestaurantList
import com.example.core.api.service.HotPepperGourmetApiService
import com.example.feature_restaurant.config.ApiConfig
import com.example.feature_restaurant.model.SearchTerms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * APIと通信するデータソース
 * @param service ホットペッパーグルメAPIサービス
 */
class RemoteRestaurantDataSource @Inject constructor(
    private val service: HotPepperGourmetApiService
) : RestaurantDataSource {

    /**
     * ホットペッパーグルメAPIを利用して、レストラン情報を取得
     * @param searchTerms 検索条件
     * @return レストラン情報 or null
     */
    override suspend fun getRestaurants(
        searchTerms: SearchTerms
    ): Response<RestaurantList>? =
        withContext(Dispatchers.IO) {
            try {
                service.searchRestaurants(
                    apiKey = ApiConfig.API_KEY,
                    keyword = searchTerms.keyword,
                    lat = searchTerms.location.latitude,
                    lng = searchTerms.location.longitude,
                    range = searchTerms.range,
                    responseFormat = ApiConfig.FORMAT,
                    count = ApiConfig.COUNT
                )
            } catch (e: Exception) {
                null
            }
        }
}