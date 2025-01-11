package com.example.feature_restaurant.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleEmptyRestaurantList
import com.example.feature_restaurant.restaurantlist.component.RestaurantListContent
import com.example.feature_restaurant.state.SearchState
import kotlinx.collections.immutable.ImmutableList

/** レストランリスト画面プレビュー */
@Composable
fun RestaurantListContentWrapper(
    searchState: SearchState,
    shops: ImmutableList<ShopsDomain> = sampleEmptyRestaurantList
) {
    RestaurantListContent(
        onRetry = {},
        popBack = {},
        searchState = searchState,
        shops = shops,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    RestaurantListContentWrapper(
        searchState = SearchState.NETWORK_ERROR
    )
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    RestaurantListContentWrapper(
        searchState = SearchState.LOADING
    )
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun EmptyPreview() {
    RestaurantListContentWrapper(
        searchState = SearchState.EMPTY_RESULT
    )
}

