package com.example.gourmetsearchercompose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.model.SampleRestaurantData.mockRestaurantData
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailContent
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailTopBar
import org.junit.Rule
import org.junit.Test

class RestaurantDetailContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRestaurantDetailContent() {
        composeTestRule.setContent {
            RestaurantDetailContent(
                paddingValues = PaddingValues(16.dp),
                restaurantData = mockRestaurantData,
                onHotPepperClick = {}
            )
        }

        // レストラン名のテスト
        composeTestRule.onNodeWithText(mockRestaurantData.name).assertIsDisplayed()
            .assertIsDisplayed()

        // 主要情報のテスト
        composeTestRule.onNodeWithText(mockRestaurantData.genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantData.budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantData.open)
            .assertIsDisplayed()

        // 詳細情報のテスト
        composeTestRule.onNodeWithText("住所").assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantData.address)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("アクセス").assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantData.access)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("休業日")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(mockRestaurantData.close).assertIsDisplayed()

        // ホットペッパーボタンのテスト
        composeTestRule.onNodeWithText("HotPepperへ").assertIsDisplayed()
    }

    @Test
    fun testRestaurantDetailTopBar() {
        composeTestRule.setContent {
            RestaurantDetailTopBar(
                title = mockRestaurantData.name,
                onClick = {}
            )
        }
        val title = mockRestaurantData.name.substring(0, 12) + "..."
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
            .assertIsDisplayed()
    }
}
