package com.example.gourmetsearchercompose.usecase.network

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

    private val mockSearchTerms = SearchTerms(
        "",
        CurrentLocation(0.0, 0.0),
        1
    )

    /** 正しくAPIが呼び出された場合のテスト */
    @Test
    fun testInvokeReturnsSuccessful() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(mockSearchTerms))
                .thenReturn(mockResponse)

            val result = getRestaurantUseCase(mockSearchTerms)

            assertEquals(mockResponse, result)
        }

    /** レスポンスがnullの場合のテスト */
    @Test
    fun testInvokeReturnsNull() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(mockSearchTerms))
                .thenReturn(null)

            val result = getRestaurantUseCase(mockSearchTerms)

            assertNull(result)
        }

    /** APIエラー時のテスト */
    @Test
    fun testInvokeReturnsError() =
        runBlocking {
            `when`(restaurantRepository.searchRestaurantRepository(mockSearchTerms))
                .thenThrow(RuntimeException("API Error"))

            try {
                getRestaurantUseCase(mockSearchTerms)
                assert(false)
            } catch (e: RuntimeException) {
                assert(e.message == "API Error")
            }
        }
}
