package com.example.gourmetsearchercompose.manager

import android.util.LruCache
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleAPIResponse
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleResponseData
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleSearchTerms
import com.example.gourmetsearchercompose.model.api.RestaurantList
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

    /** テスト前の初期化 */
    @Before
    fun setUp() {
        cacheManager = CacheManager(mockCache)
    }

    /** キャッシュにレスポンスが存在する場合のgetメソッドのテスト */
    @Test
    fun testGetCachedResponse() {
        val mockResponse = sampleAPIResponse
        `when`(mockCache[sampleSearchTerms]).thenReturn(mockResponse)

        val result = cacheManager.get(sampleSearchTerms)

        assertEquals(mockResponse, result)
    }

    /** キャッシュが空の場合のgetメソッドのテスト */
    @Test
    fun testGetEmptyCache() {
        `when`(mockCache[sampleSearchTerms]).thenReturn(null)

        val result = cacheManager.get(sampleSearchTerms)

        assertNull(result)
    }

    /** putメソッドのテスト */
    @Test
    fun testPutResponse() {
        val mockResponse = sampleAPIResponse

        cacheManager.put(sampleSearchTerms, mockResponse)

        verify(mockCache).put(sampleSearchTerms, mockResponse)
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
        val location = sampleSearchTerms.location

        repeat(maxSize) { i ->
            val terms = SearchTerms("keyword$i", location, i)
            fullCacheManager.put(terms, Response.success(sampleResponseData))
        }

        val newTerms = SearchTerms("newKeyword", location, maxSize)
        fullCacheManager.put(newTerms, Response.success(sampleResponseData))

        assertNull(fullCacheManager.get(SearchTerms("keyword0", location, 0)))
    }
}
