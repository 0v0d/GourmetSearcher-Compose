package com.example.feature_restaurant.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.PreviewWrapper
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.feature_restaurant.restaurantdetail.RestaurantDetailScreen

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
