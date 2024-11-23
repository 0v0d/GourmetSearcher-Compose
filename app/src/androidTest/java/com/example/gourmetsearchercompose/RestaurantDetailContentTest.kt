package com.example.gourmetsearchercompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantData
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.component.RestaurantDetailContent
import org.junit.Rule
import org.junit.Test

class RestaurantDetailContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRestaurantDetailContent() {
        composeTestRule.setContent {
            RestaurantDetailContent(
                restaurantData = sampleRestaurantData,
                onHotPepperClick = {},
                onMapClick = {}
            )
        }

        // レストラン名のテスト
        composeTestRule.onNodeWithText(sampleRestaurantData.name).assertIsDisplayed()
            .assertIsDisplayed()

        // 主要情報のテスト
        composeTestRule.onNodeWithText(sampleRestaurantData.genre.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantData.budget.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantData.open)
            .assertIsDisplayed()

        // 詳細情報のテスト
        composeTestRule.onNodeWithText("住所").assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantData.address)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("アクセス").assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantData.access)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("休業日")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleRestaurantData.close).assertIsDisplayed()

        // ホットペッパーボタンのテスト
        composeTestRule.onNodeWithText("HotPepperへ").assertIsDisplayed()
    }
}
