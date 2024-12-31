package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantData
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.component.RestaurantDetailContent
import com.example.gourmetsearchercompose.utils.UITestHelper
import org.junit.Rule
import org.junit.Test

class RestaurantDetailContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val testHelper = UITestHelper(composeTestRule)

    @Test
    fun testRestaurantDetailContent() {
        composeTestRule.setContent {
            RestaurantDetailContent(
                restaurantData = sampleRestaurantData,
                onHotPepperClick = {},
                onMapClick = {}
            )
        }

        // レストランの情報が表示されていることを確認
        with(sampleRestaurantData) {
            testHelper.assertTextsDisplayed(
                name,
                genre.name,
                budget.name,
                open,
                address,
                access,
                close
            )
        }

        // タイトルのテスト
        val addressTitle = context.getString(R.string.restaurant_detail_address)
        val accessTitle = context.getString(R.string.restaurant_detail_access)
        val closedDaysTitle = context.getString(R.string.restaurant_detail_closed_days)
        // ホットペッパーボタンのテスト
        val hotPepperButton = context.getString(R.string.restaurant_detail_hot_pepper)

        testHelper.assertTextsDisplayed(
            addressTitle,
            accessTitle,
            closedDaysTitle,
            hotPepperButton
        )
    }
}
