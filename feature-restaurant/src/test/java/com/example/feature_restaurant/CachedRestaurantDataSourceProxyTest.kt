package com.example.feature_restaurant

import com.example.core.api.model.client.RestaurantList
import com.example.core.api.model.client.Results
import com.example.core.cache.manager.CacheManager
import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchKey
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.source.CachedRestaurantDataSourceProxy
import com.example.feature_restaurant.source.RemoteRestaurantDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CachedRestaurantDataSourceProxyTest {
    @Mock
    private lateinit var mockDataSource: RemoteRestaurantDataSource

    @Mock
    private lateinit var mockCacheManager: CacheManager

    private lateinit var cacheDataSource: CachedRestaurantDataSourceProxy

    /** テスト前の初期化 */
    @Before
    fun setUp() {
        cacheDataSource = CachedRestaurantDataSourceProxy(
            mockDataSource, mockCacheManager
        )
    }

    /** キャッシュヒット時のテスト */
    @Test
    fun testExecuteCacheHit() = runBlocking {
        `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(sampleAPIResponse)

        val result = cacheDataSource.getRestaurants(sampleSearchTerms)

        verify(mockCacheManager).get(sampleSearchKey)
        verify(mockDataSource, never()).getRestaurants(sampleSearchTerms)
        assertEquals(sampleAPIResponse, result)
    }

    /** キャッシュミス時のテスト */
    @Test
    fun testExecuteCacheMiss() = runBlocking {
        // 準備
        `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
        `when`(mockDataSource.getRestaurants(sampleSearchTerms)).thenReturn(sampleAPIResponse)

        // 実行
        val result = cacheDataSource.getRestaurants(sampleSearchTerms)

        // 検証
        verify(mockCacheManager).get(sampleSearchKey)
        verify(mockDataSource).getRestaurants(sampleSearchTerms)
        verify(mockCacheManager).put(sampleSearchKey, sampleAPIResponse)
        assertEquals(sampleAPIResponse, result)
    }

    /** リモートデータソースがnullを返す場合のテスト */
    @Test
    fun testExecuteWithRemoteReturningNull() = runBlocking {
        `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
        `when`(mockDataSource.getRestaurants(sampleSearchTerms)).thenReturn(null)

        val result = cacheDataSource.getRestaurants(sampleSearchTerms)

        verify(mockCacheManager).get(sampleSearchKey)
        verify(mockDataSource).getRestaurants(sampleSearchTerms)
        verify(mockCacheManager, never()).put(sampleSearchKey, result)
        assertNull(result)
    }

    /** 空のレスポンスのテスト */
    @Test
    fun testExecuteWithEmptyResponse() = runBlocking {
        val emptyResponse = Response.success(
            RestaurantList(
                Results(
                    emptyList()
                )
            )
        )

        `when`(mockCacheManager.get(sampleSearchKey)).thenReturn(null)
        `when`(mockDataSource.getRestaurants(sampleSearchTerms)).thenReturn(emptyResponse)

        val result = cacheDataSource.getRestaurants(sampleSearchTerms)

        verify(mockCacheManager).get(sampleSearchKey)
        verify(mockDataSource).getRestaurants(sampleSearchTerms)
        verify(mockCacheManager).put(sampleSearchKey, emptyResponse)
        assertEquals(emptyResponse, result)
    }
}