package com.example.feature_restaurant

import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.repository.RestaurantRepository
import com.example.feature_restaurant.usecase.GetRestaurantUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** GetHotPepperDataUseCaseのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class GetRestaurantListUseCaseTest {
    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @InjectMocks
    private lateinit var getRestaurantUseCase: GetRestaurantUseCase

    /** 正しくAPIが呼び出された場合のテスト */
    @Test
    fun testInvokeReturnsSuccessful() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(sampleSearchTerms))
                .thenReturn(sampleAPIResponse)

            val result = getRestaurantUseCase(sampleSearchTerms)

            assertEquals(sampleAPIResponse, result)
        }

    /** レスポンスがnullの場合のテスト */
    @Test
    fun testInvokeReturnsNull() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(sampleSearchTerms))
                .thenReturn(null)

            val result = getRestaurantUseCase(sampleSearchTerms)

            assertNull(result)
        }

    /** APIエラー時のテスト */
    @Test
    fun testInvokeReturnsError() =
        runBlocking {
            val errorMessage = "API Error"
            `when`(restaurantRepository.searchRestaurantRepository(sampleSearchTerms))
                .thenThrow(RuntimeException(errorMessage))

            try {
                getRestaurantUseCase(sampleSearchTerms)
                assert(false)
            } catch (e: RuntimeException) {
                assert(e.message == errorMessage)
            }
        }
}
