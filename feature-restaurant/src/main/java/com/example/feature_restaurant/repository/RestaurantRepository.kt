package com.example.feature_restaurant.repository

import com.example.core.cache.manager.CacheManager
import com.example.core.api.model.client.RestaurantList
import com.example.core.api.service.HotPepperGourmetApiService
import com.example.feature_restaurant.BuildConfig
import com.example.feature_restaurant.model.SearchTerms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.text.ParseException
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
 * @param service ホットペッパーグルメAPIのインターフェース
 */
class RestaurantRepositoryImpl
@Inject
constructor(
    private val service: HotPepperGourmetApiService,
    private val cacheManager: CacheManager,
) : RestaurantRepository {
    /** APIキー */
    private val key = BuildConfig.API_KEY

    /** レスポンスフォーマット */
    private val format = "json"

    /** 結果表示件数 */
    private val count = 50

    /** リポジトリ情報を取得
     * @param searchTerms 検索条件
     * @return レストラン情報 or null
     */
    override suspend fun searchRestaurantRepository(searchTerms: SearchTerms): Response<RestaurantList>? {
        return searchGourmetShopRepository(searchTerms)
    }

    /**
     * ホットペッパーグルメAPIを利用して、レストラン情報を取得
     * @param searchTerms 検索条件
     * @return レストラン情報 or null
     */
    private suspend fun searchGourmetShopRepository(searchTerms: SearchTerms): Response<RestaurantList>? =
        withContext(Dispatchers.IO) {
            // キャッシュから結果を取得
            val searchKey = searchTerms.toSearchKey()
            cacheManager.get(searchKey)?.let { return@withContext it }

            return@withContext try {
                val response =
                    service.searchRestaurants(
                        apiKey = key,
                        keyword = searchTerms.keyword,
                        lat = searchTerms.location.latitude,
                        lng = searchTerms.location.longitude,
                        range = searchTerms.range,
                        responseFormat = format,
                        count = count
                    )
                cacheManager.put(searchKey, response)
                response
            } catch (e: IOException) {
                null
            } catch (e: HttpException) {
                null
            } catch (e: ParseException) {
                null
            } catch (e: Exception) {
                null
            }
        }
}
