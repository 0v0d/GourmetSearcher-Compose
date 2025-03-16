package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.feature_restaurant.R
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.feature_restaurant.restaurantdetail.component.RestaurantDetailContent
import org.junit.Rule
import org.junit.Test

class RestaurantDetailContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val testHelper = UITestHelper(composeTestRule)

    /** レストランの詳細情報が表示されることを確認 */
    @Test
    fun testShowRestaurantDetail() {
        composeTestRule.setContent {
            RestaurantDetailContent(
                restaurantData = sampleRestaurantList.first(),
                onHotPepperClick = {},
                onMapClick = {}
            )
        }

        // レストランの情報が表示されていることを確認
        with(sampleRestaurantList.first()) {
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

        val addressTitle = context.getString(R.string.restaurant_detail_address)
        val accessTitle = context.getString(R.string.restaurant_detail_access)
        val closedDaysTitle = context.getString(R.string.restaurant_detail_closed_days)
        val hotPepperButton = context.getString(R.string.restaurant_detail_hot_pepper)

        // 住所、アクセス、定休日、ホットペッパーボタンが表示されていることを確認
        testHelper.assertTextsDisplayed(
            addressTitle,
            accessTitle,
            closedDaysTitle,
            hotPepperButton
        )
    }
}
