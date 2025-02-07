package com.example.feature_restaurant

import android.util.LruCache
import com.example.core.cache.manager.CacheManager
import com.example.core.api.model.client.RestaurantList
import com.example.core.api.model.data.CurrentLocation
import com.example.core.api.model.data.SearchKey
import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockRestaurantData.sampleResponseData
import com.example.feature_restaurant.mock.MockSearchTerms.cacheKey
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchKey
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
    private lateinit var mockCache: LruCache<String, Response<RestaurantList>?>

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
        `when`(mockCache[cacheKey]).thenReturn(mockResponse)

        val result = cacheManager.get(sampleSearchKey)

        assertEquals(mockResponse, result)
    }

    /** キャッシュが空の場合のgetメソッドのテスト */
    @Test
    fun testGetEmptyCache() {
        `when`(mockCache[cacheKey]).thenReturn(null)

        val result = cacheManager.get(sampleSearchKey)

        assertNull(result)
    }

    /** putメソッドのテスト */
    @Test
    fun testPutResponse() {
        val mockResponse = sampleAPIResponse

        cacheManager.put(sampleSearchKey, mockResponse)

        verify(mockCache).put(cacheKey, mockResponse)
    }

    /** 無効な入力でのgetメソッドのテスト */
    @Test
    fun testGetWithInvalidInput() {
        val invalidSearchTerms = SearchKey(
            "",
            CurrentLocation(0.0, 0.0),
            0
        )
        val result = cacheManager.get(invalidSearchTerms)
        assertNull(result)
    }

    /** キャッシュが満杯の場合のputメソッドのテスト */
    @Test
    fun testPutWhenCacheIsFull() {
        val maxSize = 5
        val fullCache = mockCache
        val fullCacheManager = CacheManager(fullCache)
        val location = CurrentLocation(0.0, 0.0)

        repeat(maxSize) { i ->
            val terms = SearchKey("keyword$i", location, i)
            fullCacheManager.put(terms, Response.success(sampleResponseData))
        }
        val newTerms = SearchKey("newKeyword", location, maxSize)
        fullCacheManager.put(newTerms, Response.success(sampleResponseData))

        assertNull(fullCacheManager.get(SearchKey("keyword0", location, maxSize)))
    }
}
