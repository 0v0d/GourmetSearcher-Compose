package com.example.feature_restaurant

import com.example.core.model.api.RestaurantList
import com.example.core.model.api.Results
import com.example.core.model.api.Shops
import com.example.feature_restaurant.domain.toDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleAPIResponse
import com.example.feature_restaurant.mock.MockSearchTerms.sampleSearchTerms
import com.example.feature_restaurant.state.SearchState
import com.example.feature_restaurant.usecase.GetRestaurantUseCase
import com.example.feature_restaurant.viewmodel.RestaurantListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/** RestaurantListViewModelのユニットテストクラス */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RestaurantListViewModelTest {
    @Mock
    private lateinit var getRestaurantUseCase: GetRestaurantUseCase

    @InjectMocks
    private lateinit var viewModel: RestaurantListViewModel

    private val mockEmptyResponse =
        RestaurantList(
            Results(
                emptyList(),
            ),
        )

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    /** 各テスト後のクリーンアップ */
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    /** ホットペッパーグルメAPIからのレスポンスが成功した場合のテスト */
    @Test
    fun testSearchRestaurantsSuccess() = runTest {
        val expectedShops =
            sampleAPIResponse.body()?.results?.shops?.map { it.toDomain() } ?: emptyList()
        `when`(getRestaurantUseCase(sampleSearchTerms)).thenReturn(
            Response.success(
                sampleAPIResponse.body()
            )
        )

        viewModel.searchRestaurants(sampleSearchTerms)
        viewModel.shops.first()?.map { assertEquals(expectedShops, it) }
    }

    /** ホットペッパーグルメAPIからのレスポンスが失敗した場合のテスト */
    @Test
    fun testSearchRestaurantsNetworkError() =
        runTest {
            `when`(getRestaurantUseCase(sampleSearchTerms)).thenReturn(null)

            viewModel.searchRestaurants(sampleSearchTerms)

            assertEquals(SearchState.NETWORK_ERROR, viewModel.searchState.value)
        }

    /** ホットペッパーグルメAPIからのレスポンスが空の場合のテスト */
    @Test
    fun testSearchRestaurantsEmptyResponse() =
        runTest {
            `when`(getRestaurantUseCase(sampleSearchTerms)).thenReturn(
                Response.success(
                    mockEmptyResponse
                )
            )

            viewModel.searchRestaurants(sampleSearchTerms)
            val shops = mockEmptyResponse.results.shops.map { it.toDomain() }
            assertEquals(emptyList<Shops>(), shops)
            assertEquals(SearchState.EMPTY_RESULT, viewModel.searchState.value)
        }

    /** 検索のリトライテスト */
    @Test
    fun testRetrySearch() =
        runTest {
            val mockResponse = mock<Response<RestaurantList>>()
            `when`(getRestaurantUseCase(sampleSearchTerms)).thenReturn(mockResponse)

            viewModel.searchRestaurants(sampleSearchTerms)
            viewModel.retrySearch()

            verify(getRestaurantUseCase, times(2)).invoke(sampleSearchTerms)
        }
}
