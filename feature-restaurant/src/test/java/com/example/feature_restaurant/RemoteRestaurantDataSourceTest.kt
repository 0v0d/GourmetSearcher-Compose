package com.example.feature_restaurant

import com.example.core.api.service.HotPepperGourmetApiService
import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.source.RemoteRestaurantDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** RemoteRestaurantDataSourceのユニットテスト */
@RunWith(MockitoJUnitRunner::class)
class RemoteRestaurantDataSourceTest {
    @Mock
    private lateinit var service: HotPepperGourmetApiService

    private lateinit var dataSource: RemoteRestaurantDataSource

    /** テスト前の初期化 */
    @Before
    fun setUp() {
        dataSource = RemoteRestaurantDataSource(service)
    }

    @Test
    fun testGetRestaurants() =
        runBlocking {
            `when`(
                service.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                )
            ).thenReturn(sampleAPIResponse)

            val result = dataSource.getRestaurants(sampleSearchTerms)

            assertEquals(sampleAPIResponse, result)
        }

    @Test
    fun testGetRestaurantsError() =
        runBlocking {
            `when`(
                service.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                )
            ).thenThrow(RuntimeException())

            val result = dataSource.getRestaurants(sampleSearchTerms)

            assertEquals(null, result)
        }

    @Test
    fun testGetRestaurantsEmpty() =
        runBlocking {
            `when`(
                service.searchRestaurants(
                    anyString(),
                    anyString(),
                    anyDouble(),
                    anyDouble(),
                    anyInt(),
                    anyString(),
                    anyInt(),
                )
            ).thenReturn(null)

            val result = dataSource.getRestaurants(sampleSearchTerms)

            assertEquals(null, result)
        }
}