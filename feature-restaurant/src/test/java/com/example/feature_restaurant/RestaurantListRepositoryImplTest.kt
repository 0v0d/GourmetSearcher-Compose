package com.example.feature_restaurant

import com.example.core.manager.CacheManager
import com.example.core.model.api.RestaurantList
import com.example.core.model.api.Results
import com.example.core.service.HotPepperGourmetApiService
import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchKey
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.repository.RestaurantRepository
import com.example.feature_restaurant.repository.RestaurantRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/** RestaurantListRepositoryImplのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class RestaurantListRepositoryImplTest {
    @Mock
    private lateinit var mockService: HotPepperGourmetApiService

    @Mock
    private lateinit var mockCacheManager: CacheManager

    private lateinit var restaurantRepository: RestaurantRepository

    private val responseFormat = "json"

    private val count = 50

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        restaurantRepository =
            RestaurantRepositoryImpl(mockService, mockCacheManager)
    }

    /** キャッシュヒット時のテスト */
    @Test
    fun testExecuteCacheHit() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(sampleAPIResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchKey)
            verify(mockService, never()).searchRestaurants(
                anyString(),
                anyString(),
                anyDouble(),
                anyDouble(),
                anyInt(),
                anyString(),
                anyInt(),
            )
            assertEquals(sampleAPIResponse, result)
        }

    /** キャッシュミス時のテスト */
    @Test
    fun testExecuteCacheMiss() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                ),
            ).thenReturn(sampleAPIResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchKey)

            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat,
                    count
                )
            }
            verify(mockCacheManager).put(sampleSearchKey, sampleAPIResponse)
            assertEquals(sampleAPIResponse, result)
        }

    /** API例外時のテスト */
    @Test
    fun testExecuteWithException() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                ),
            ).thenThrow(RuntimeException::class.java)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchKey)
            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat,
                    count
                )
            }

            verify(mockCacheManager, never()).put(sampleSearchKey, sampleAPIResponse)
            assertNull(result)
        }

    /** 空のレスポンスのテスト */
    @Test
    fun testExecuteWithEmptyResponse() =
        runBlocking {
            val emptyResponse = Response.success(
                RestaurantList(
                    Results(
                        emptyList()
                    )
                )
            )
            `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                ),
            ).thenReturn(emptyResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchKey)
            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat,
                    count
                )
            }
            verify(mockCacheManager).put(sampleSearchKey, emptyResponse)
            assertEquals(emptyResponse, result)
        }
}
