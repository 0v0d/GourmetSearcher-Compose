package com.example.gourmetsearchercompose.repository

import com.example.gourmetsearchercompose.BuildConfig
import com.example.gourmetsearchercompose.manager.CacheManager
import com.example.gourmetsearchercompose.model.api.BudgetData
import com.example.gourmetsearchercompose.model.api.GenreData
import com.example.gourmetsearchercompose.model.api.LargeAreaData
import com.example.gourmetsearchercompose.model.api.PCData
import com.example.gourmetsearchercompose.model.api.PhotoData
import com.example.gourmetsearchercompose.model.api.RestaurantList
import com.example.gourmetsearchercompose.model.api.Results
import com.example.gourmetsearchercompose.model.api.Shops
import com.example.gourmetsearchercompose.model.api.SmallAreaData
import com.example.gourmetsearchercompose.model.api.Urls
import com.example.gourmetsearchercompose.model.data.CurrentLocation
import com.example.gourmetsearchercompose.model.data.SearchTerms
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

    private val mockSearchTerms = SearchTerms(
        "keyword",
        CurrentLocation(35.0, 139.0),
        1
    )

    private val mockResponse =
        Response.success(
            RestaurantList(
                Results(
                    listOf(
                        Shops(
                            "Restaurant",
                            "Address",
                            "Station",
                            LargeAreaData("Large Area"),
                            SmallAreaData("Small Area"),
                            GenreData("Genre"),
                            BudgetData("Budget"),
                            "Access",
                            Urls("URL"),
                            PhotoData(PCData("Photo URL")),
                            "Open",
                            "Close",
                        ),
                    ),
                ),
            ),
        )

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        restaurantRepository = RestaurantRepositoryImpl(mockService, mockCacheManager)
    }

    /** キャッシュヒット時のテスト */
    @Test
    fun testExecuteCacheHit() =
        runBlocking {
            `when`(mockCacheManager.get(mockSearchTerms)).thenReturn(mockResponse)

            val result = restaurantRepository.searchRestaurantRepository(mockSearchTerms)

            verify(mockCacheManager).get(mockSearchTerms)
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
            `when`(mockCacheManager.get(mockSearchTerms)).thenReturn(null)
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

            val result = restaurantRepository.searchRestaurantRepository(mockSearchTerms)

            verify(mockCacheManager).get(mockSearchTerms)
            verify(mockService).searchRestaurants(
                BuildConfig.API_KEY,
                mockSearchTerms.keyword,
                mockSearchTerms.location.latitude,
                mockSearchTerms.location.longitude,
                mockSearchTerms.range,
                "json",
            )
            verify(mockCacheManager).put(mockSearchTerms, mockResponse)
            assertEquals(mockResponse, result)
        }

    /** API例外時のテスト */
    @Test
    fun testExecuteWithException() =
        runBlocking {
            `when`(mockCacheManager.get(mockSearchTerms)).thenReturn(null)
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

            val result = restaurantRepository.searchRestaurantRepository(mockSearchTerms)

            verify(mockCacheManager).get(mockSearchTerms)
            verify(mockService).searchRestaurants(
                BuildConfig.API_KEY,
                mockSearchTerms.keyword,
                mockSearchTerms.location.latitude,
                mockSearchTerms.location.longitude,
                mockSearchTerms.range,
                "json",
            )
            verify(mockCacheManager, never()).put(mockSearchTerms, mockResponse)
            assertNull(result)
        }

    /** 空のレスポンスのテスト */
    @Test
    fun testExecuteWithEmptyResponse() =
        runBlocking {
            val emptyResponse = Response.success(RestaurantList(Results(emptyList())))
            `when`(mockCacheManager.get(mockSearchTerms)).thenReturn(null)
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

            val result = restaurantRepository.searchRestaurantRepository(mockSearchTerms)

            verify(mockCacheManager).get(mockSearchTerms)
            verify(mockService).searchRestaurants(
                BuildConfig.API_KEY,
                mockSearchTerms.keyword,
                mockSearchTerms.location.latitude,
                mockSearchTerms.location.longitude,
                mockSearchTerms.range,
                "json",
            )
            verify(mockCacheManager).put(mockSearchTerms, emptyResponse)
            assertEquals(emptyResponse, result)
        }
}
