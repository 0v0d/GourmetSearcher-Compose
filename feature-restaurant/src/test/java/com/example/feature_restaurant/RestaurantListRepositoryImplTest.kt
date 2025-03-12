package com.example.feature_restaurant

import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.repository.RestaurantRepository
import com.example.feature_restaurant.repository.RestaurantRepositoryImpl
import com.example.feature_restaurant.source.CachedRestaurantDataSourceProxy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** RestaurantListRepositoryImplのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class RestaurantListRepositoryImplTest {
    @Mock
    private lateinit var dataSourceProxy: CachedRestaurantDataSourceProxy

    private lateinit var restaurantRepository: RestaurantRepository

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        restaurantRepository =
            RestaurantRepositoryImpl(dataSourceProxy)
    }

    /** キャッシュヒット時のテスト */
    @Test
    fun testExecuteCacheHit() =
        runBlocking {
            `when`(dataSourceProxy.getRestaurants(sampleSearchTerms)).thenReturn(sampleAPIResponse)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(dataSourceProxy).getRestaurants(sampleSearchTerms)
            assertEquals(sampleAPIResponse, result)
        }

    /** キャッシュミス時のテスト */
    @Test
    fun testExecuteCacheMiss() =
        runBlocking {
            `when`(dataSourceProxy.getRestaurants(sampleSearchTerms)).thenReturn(null)

            val result = restaurantRepository.searchRestaurantRepository(sampleSearchTerms)

            verify(dataSourceProxy).getRestaurants(sampleSearchTerms)
            assertNull(result)
        }
}
