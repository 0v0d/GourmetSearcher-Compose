package com.example.gourmetsearchercompose.usecase.network

import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleResponseData
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleSearchTerms
import com.example.gourmetsearchercompose.repository.RestaurantRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/** GetHotPepperDataUseCaseのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class GetRestaurantListUseCaseTest {
    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @InjectMocks
    private lateinit var getRestaurantUseCase: GetRestaurantUseCase

    private val mockResponse =
        Response.success(
            sampleResponseData
        )

    /** 正しくAPIが呼び出された場合のテスト */
    @Test
    fun testInvokeReturnsSuccessful() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(sampleSearchTerms))
                .thenReturn(mockResponse)

            val result = getRestaurantUseCase(sampleSearchTerms)

            assertEquals(mockResponse, result)
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
