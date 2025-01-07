package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.feature_restaurant.R
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

    @Test
    fun testRestaurantListContentSuccess() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.SUCCESS,
                shops = sampleRestaurantList,
                onNavigateToDetail = {}
            )
        }

        for (restaurant in sampleRestaurantList) {
            testHelper.assertTextsDisplayed(restaurant.name)
        }
    }

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

    @Test
    fun testRestaurantList() {
        composeTestRule.setContent {
            RestaurantRow(
                shops = sampleRestaurantList,
                navigateToDetail = {}
            )
        }

        for (restaurant in sampleRestaurantList) {
            testHelper.assertTextsDisplayed(restaurant.name)
        }
    }

    @Test
    fun testRestaurantItem() {
        val clicked = mutableStateOf(false)
        composeTestRule.setContent {
            RestaurantItem(
                restaurant = sampleRestaurantList[0],
                onClick = { clicked.value = true }
            )
        }


        with(sampleRestaurantList[0]) {
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

    @Test
    fun testRestaurantInfo() {
        composeTestRule.setContent {
            RestaurantInfo(restaurant = sampleRestaurantList[0])
        }

        with(sampleRestaurantList[0]) {
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