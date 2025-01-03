package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailScreen

/** レストラン詳細画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun Preview() {
    PreviewWrapper {
        RestaurantDetailScreen(
            restaurantData = sampleRestaurantList[0]
        )
    }
}

/** レストラン詳細画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun DarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantDetailScreen(
            restaurantData = sampleRestaurantList[0]
        )
    }
}
