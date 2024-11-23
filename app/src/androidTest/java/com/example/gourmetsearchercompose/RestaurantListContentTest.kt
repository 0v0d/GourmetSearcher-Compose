package com.example.gourmetsearchercompose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantInfo
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantItem
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantListContent
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantRow
import org.junit.Rule
import org.junit.Test

class RestaurantListContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRestaurantListContent_Success() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.SUCCESS,
                shops = sampleRestaurantList,
                onNavigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText(sampleRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[1].name).assertIsDisplayed()
    }

    @Test
    fun testRestaurantListContent_EmptyResult() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.EMPTY_RESULT,
                shops = null,
                onNavigateToDetail = {},
            )
        }

        composeTestRule.onNodeWithText("検索結果がありませんでした").assertIsDisplayed()
    }

    @Test
    fun testRestaurantListContent_NetworkError() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                popBack = {},
                searchState = SearchState.NETWORK_ERROR,
                shops = null,
                onNavigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText("ネットワークエラーが発生しました").assertIsDisplayed()
        composeTestRule.onNodeWithText("リトライ").assertIsDisplayed()
    }

    @Test
    fun testRestaurantList() {
        composeTestRule.setContent {
            RestaurantRow(
                shops = sampleRestaurantList,
                navigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText(sampleRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[1].name).assertIsDisplayed()
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

        composeTestRule.onNodeWithText(sampleRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText("${sampleRestaurantList[0].smallArea.name}[${sampleRestaurantList[0].largeArea.name}]")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].access).assertIsDisplayed()

        composeTestRule.onNodeWithText(sampleRestaurantList[0].name).performClick()
        assert(clicked.value)
    }

    @Test
    fun testRestaurantInfo() {
        composeTestRule.setContent {
            RestaurantInfo(restaurant = sampleRestaurantList[0])
        }

        composeTestRule.onNodeWithText(sampleRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText("${sampleRestaurantList[0].smallArea.name}[${sampleRestaurantList[0].largeArea.name}]")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantList[0].access).assertIsDisplayed()
    }
}