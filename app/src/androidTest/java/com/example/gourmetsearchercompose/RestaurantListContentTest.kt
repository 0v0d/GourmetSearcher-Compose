package com.example.gourmetsearchercompose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.gourmetsearchercompose.model.SampleRestaurantData.mockRestaurantList
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantInfo
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantItem
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantList
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantListContent
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
                searchState = SearchState.Success,
                shops = mockRestaurantList,
                onNavigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText(mockRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[1].name).assertIsDisplayed()
    }

    @Test
    fun testRestaurantListContent_EmptyResult() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                searchState = SearchState.EmptyResult,
                shops = null,
                onNavigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText("検索結果がありませんでした").assertIsDisplayed()
    }

    @Test
    fun testRestaurantListContent_NetworkError() {
        composeTestRule.setContent {
            RestaurantListContent(
                onRetry = {},
                searchState = SearchState.NetworkError,
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
            RestaurantList(
                shops = mockRestaurantList,
                navigateToDetail = {}
            )
        }

        composeTestRule.onNodeWithText(mockRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[1].name).assertIsDisplayed()
    }

    @Test
    fun testRestaurantItem() {
        val clicked = mutableStateOf(false)
        composeTestRule.setContent {
            RestaurantItem(
                restaurant = mockRestaurantList[0],
                onClick = { clicked.value = true }
            )
        }

        composeTestRule.onNodeWithText(mockRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText("${mockRestaurantList[0].smallArea.name}[${mockRestaurantList[0].largeArea.name}]")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].access).assertIsDisplayed()

        composeTestRule.onNodeWithText(mockRestaurantList[0].name).performClick()
        assert(clicked.value)
    }

    @Test
    fun testRestaurantInfo() {
        composeTestRule.setContent {
            RestaurantInfo(restaurant = mockRestaurantList[0])
        }

        composeTestRule.onNodeWithText(mockRestaurantList[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText("${mockRestaurantList[0].smallArea.name}[${mockRestaurantList[0].largeArea.name}]")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantList[0].access).assertIsDisplayed()
    }
}