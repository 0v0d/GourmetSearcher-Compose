package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.model.SampleRestaurantData.mockRestaurantData
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailScreen

/** レストラン詳細画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantDetailContentPreview() {
    RestaurantDetailScreen(
        restaurantData = mockRestaurantData
    )
}

/** レストラン詳細画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantDetailContentDarkPreview() {
    AppTheme(darkTheme = true) {
        RestaurantDetailScreen(
            restaurantData = mockRestaurantData
        )
    }
}
