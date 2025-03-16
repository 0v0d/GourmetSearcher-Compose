package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.core.app.ApplicationProvider
import com.example.feature_restaurant.R
import com.example.feature_restaurant.mock.MockRestaurantData.samplePagingRestaurantList
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.feature_restaurant.restaurantlist.component.RestaurantInfo
import com.example.feature_restaurant.restaurantlist.component.RestaurantItem
import com.example.feature_restaurant.restaurantlist.component.RestaurantListContent
import com.example.feature_restaurant.restaurantlist.component.RestaurantRow
import com.example.feature_restaurant.state.SearchState
import org.junit.Rule
import org.junit.Test

class RestaurantListContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val testHelper = UITestHelper(composeTestRule)

    /** レストランリストのコンテンツが正常に表示されることを確認 */
    @Test
    fun testRestaurantListSuccess() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.SUCCESS,
                shops = samplePagingRestaurantList.collectAsLazyPagingItems(),
                onNavigateToDetail = {}
            )
        }

        for (restaurant in sampleRestaurantList) {
            testHelper.assertTextsDisplayed(restaurant.name)
        }
    }

    /** レストランリストのコンテンツが空の場合の表示を確認 */
    @Test
    fun testRestaurantListContentEmptyResult() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.EMPTY_RESULT,
                shops = null,
                onNavigateToDetail = {},
            )
        }

        val emptyResultMessage = context.getString(R.string.restaurant_list_empty_result_message)
        testHelper.assertTextsDisplayed(emptyResultMessage)
    }

    /** レストランリストのコンテンツがネットワークエラーの場合の表示を確認 */
    @Test
    fun testRestaurantListContentNetworkError() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.NETWORK_ERROR,
                shops = null,
                onNavigateToDetail = {}
            )
        }

        val networkErrorMessage = context.getString(R.string.restaurant_list_network_error_message)
        val retryLabel = context.getString(com.example.shared_ui.R.string.common_retry)

        testHelper.assertTextsDisplayed(
            networkErrorMessage,
            retryLabel
        )
    }

    /** レストランリストの行が正しく表示されているかのテスト */
    @Test
    fun testRestaurantList() {
        composeTestRule.setContent {
            RestaurantRow(
                shops = samplePagingRestaurantList.collectAsLazyPagingItems(),
                navigateToDetail = {},
                listState = rememberLazyListState()
            )
        }

        for (restaurant in sampleRestaurantList) {
            testHelper.assertTextsDisplayed(restaurant.name)
        }
    }

    /** レストランアイテムが正しく表示されているかのテスト */
    @Test
    fun testRestaurantItem() {
        val clicked = mutableStateOf(false)
        composeTestRule.setContent {
            RestaurantItem(
                restaurant = sampleRestaurantList.first(),
                onClick = { clicked.value = true }
            )
        }


        with(sampleRestaurantList.first()) {
            testHelper.assertTextsDisplayed(
                name,
                "${smallArea.name}[${largeArea.name}]",
                genre.name,
                budget.name,
                access
            )

            composeTestRule.onNodeWithText(name).performClick()
        }
        assert(clicked.value)
    }

    /** レストラン情報が正しく表示されているかのテスト */
    @Test
    fun testRestaurantInfo() {
        composeTestRule.setContent {
            RestaurantInfo(restaurant = sampleRestaurantList.first())
        }

        with(sampleRestaurantList.first()) {
            testHelper.assertTextsDisplayed(
                name,
                "${smallArea.name}[${largeArea.name}]",
                genre.name,
                budget.name,
                access
            )
        }
    }
}