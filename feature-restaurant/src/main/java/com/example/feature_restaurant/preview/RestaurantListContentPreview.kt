package com.example.feature_restaurant.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.samplePagingRestaurantList
import com.example.feature_restaurant.restaurantlist.component.RestaurantListContent
import com.example.feature_restaurant.state.SearchState

/** レストランリスト画面プレビュー */
@Composable
fun RestaurantListContentWrapper(
    searchState: SearchState,
    shops: LazyPagingItems<ShopsDomain> = samplePagingRestaurantList
        .collectAsLazyPagingItems()
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

