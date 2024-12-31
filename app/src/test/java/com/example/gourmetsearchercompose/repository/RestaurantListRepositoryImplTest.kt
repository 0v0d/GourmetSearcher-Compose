package com.example.gourmetsearchercompose.repository

import com.example.gourmetsearchercompose.BuildConfig
import com.example.gourmetsearchercompose.manager.CacheManager
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleResponseData
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleSearchTerms
import com.example.gourmetsearchercompose.model.api.RestaurantList
import com.example.gourmetsearchercompose.model.api.Results
import com.example.gourmetsearchercompose.service.HotPepperGourmetApiService
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

    private val mockResponse = Response.success(sampleResponseData)

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        restaurantRepository = RestaurantRepositoryImpl(mockService, mockCacheManager)
    }

    /** キャッシュヒット時のテスト */
    @Test
    fun testExecuteCacheHit() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchTerms)).thenReturn(mockResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchTerms)
            verify(mockService, never()).searchRestaurants(
                anyString(),
                anyString(),
                anyDouble(),
                anyDouble(),
                anyInt(),
                anyString(),
            )
            assertEquals(mockResponse, result)
        }

    /** キャッシュミス時のテスト */
    @Test
    fun testExecuteCacheMiss() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchTerms)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                ),
            ).thenReturn(mockResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchTerms)

            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat
                )
            }
            verify(mockCacheManager).put(sampleSearchTerms, mockResponse)
            assertEquals(mockResponse, result)
        }

    /** API例外時のテスト */
    @Test
    fun testExecuteWithException() =
        runBlocking {
            `when`(mockCacheManager.get(sampleSearchTerms)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                ),
            ).thenThrow(RuntimeException::class.java)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchTerms)
            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat,
                )
            }

            verify(mockCacheManager, never()).put(sampleSearchTerms, mockResponse)
            assertNull(result)
        }

    /** 空のレスポンスのテスト */
    @Test
    fun testExecuteWithEmptyResponse() =
        runBlocking {
            val emptyResponse = Response.success(RestaurantList(Results(emptyList())))
            `when`(mockCacheManager.get(sampleSearchTerms)).thenReturn(null)
            `when`(
                mockService.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                ),
            ).thenReturn(emptyResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(mockCacheManager).get(sampleSearchTerms)
            with(sampleSearchTerms) {
                verify(mockService).searchRestaurants(
                    BuildConfig.API_KEY,
                    keyword,
                    location.latitude,
                    location.longitude,
                    range,
                    responseFormat,
                )
            }
            verify(mockCacheManager).put(sampleSearchTerms, emptyResponse)
            assertEquals(emptyResponse, result)
        }
}
