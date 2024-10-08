package com.example.gourmetsearchercompose.manager

import android.util.LruCache
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/** CacheManagerクラスのユニットテスト */
@RunWith(MockitoJUnitRunner::class)
class CacheManagerTest {
    @Mock
    private lateinit var mockCache: LruCache<SearchTerms, Response<RestaurantList>?>

    private lateinit var cacheManager: CacheManager

    private val mockSearchTerms = SearchTerms(
        "keyword",
        CurrentLocation(35.0, 139.0),
        1
    )

    private val mockRestaurantList =
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
        )

    /** テスト前の初期化 */
    @Before
    fun setUp() {
        cacheManager = CacheManager(mockCache)
    }

    /** キャッシュにレスポンスが存在する場合のgetメソッドのテスト */
    @Test
    fun testGetCachedResponse() {
        val mockResponse = Response.success(mockRestaurantList)
        `when`(mockCache[mockSearchTerms]).thenReturn(mockResponse)

        val result = cacheManager.get(mockSearchTerms)

        assertEquals(mockResponse, result)
    }

    /** キャッシュが空の場合のgetメソッドのテスト */
    @Test
    fun testGetEmptyCache() {
        `when`(mockCache[mockSearchTerms]).thenReturn(null)

        val result = cacheManager.get(mockSearchTerms)

        assertNull(result)
    }

    /** putメソッドのテスト */
    @Test
    fun testPutResponse() {
        val mockResponse = Response.success(mockRestaurantList)

        cacheManager.put(mockSearchTerms, mockResponse)

        verify(mockCache).put(mockSearchTerms, mockResponse)
    }

    /** 無効な入力でのgetメソッドのテスト */
    @Test
    fun testGetWithInvalidInput() {
        val invalidSearchTerms = SearchTerms("", CurrentLocation(0.0, 0.0), 0)
        val result = cacheManager.get(invalidSearchTerms)
        assertNull(result)
    }

    /** キャッシュが満杯の場合のputメソッドのテスト */
    @Test
    fun testPutWhenCacheIsFull() {
        val maxSize = 5
        val fullCache = mockCache
        val fullCacheManager = CacheManager(fullCache)

        repeat(maxSize) { i ->
            val terms = SearchTerms("keyword$i", CurrentLocation(35.0, 139.0), i)
            fullCacheManager.put(terms, Response.success(mockRestaurantList))
        }

        val newTerms = SearchTerms("newKeyword", CurrentLocation(35.0, 139.0), maxSize)
        fullCacheManager.put(newTerms, Response.success(mockRestaurantList))

        assertNull(fullCacheManager.get(SearchTerms("keyword0", CurrentLocation(35.0, 139.0), 0)))
    }
}
