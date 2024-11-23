package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleEmptyRestaurantList
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantListContent
import kotlinx.collections.immutable.ImmutableList

/** レストランリスト画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun SuccessPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.SUCCESS,
            shops = sampleRestaurantList
        )
    }
}

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
@Preview
@Composable
private fun ErrorPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.NETWORK_ERROR
        )
    }
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun LoadingPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.LOADING
        )
    }
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun EmptyPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.EMPTY_RESULT
        )
    }
}

/** レストランリスト画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun SuccessDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.SUCCESS,
            shops = sampleRestaurantList
        )
    }
}

/** レストランリスト画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun ErrorDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.NETWORK_ERROR
        )
    }
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun LoadingDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.LOADING
        )
    }
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun EmptyDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.EMPTY_RESULT
        )
    }
}
