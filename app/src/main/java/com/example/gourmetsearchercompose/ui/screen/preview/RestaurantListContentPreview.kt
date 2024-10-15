package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.model.SampleRestaurantData.mockEmptyRestaurantList
import com.example.gourmetsearchercompose.model.SampleRestaurantData.mockRestaurantList
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantListContent

/** レストランリスト画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenSuccessPreview() {
    RestaurantListContent(
        onRetry = {},
        searchState = SearchState.Success,
        shops = mockRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenErrorPreview() {
    RestaurantListContent(
        onRetry = {},
        searchState = SearchState.NetworkError,
        shops = mockEmptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenLoadingPreview() {
    RestaurantListContent(
        onRetry = {},
        searchState = SearchState.Loading,
        shops = mockEmptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenEmptyPreview() {
    RestaurantListContent(
        onRetry = {},
        searchState = SearchState.EmptyResult,
        shops = mockEmptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenSuccessDarkPreview() {
    AppTheme(darkTheme = true) {
        RestaurantListContent(
            onRetry = {},
            searchState = SearchState.Success,
            shops = mockRestaurantList,
            onNavigateToDetail = {}
        )
    }
}

/** レストランリスト画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenErrorDarkPreview() {
    AppTheme(darkTheme = true) {
        RestaurantListContent(
            onRetry = {},
            searchState = SearchState.NetworkError,
            shops = mockEmptyRestaurantList,
            onNavigateToDetail = {}
        )
    }
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenLoadingDarkPreview() {
    AppTheme(darkTheme = true) {
        RestaurantListContent(
            onRetry = {},
            searchState = SearchState.Loading,
            shops = mockEmptyRestaurantList,
            onNavigateToDetail = {}
        )
    }
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenEmptyDarkPreview() {
    AppTheme(darkTheme = true) {
        RestaurantListContent(
            onRetry = {},
            searchState = SearchState.EmptyResult,
            shops = mockEmptyRestaurantList,
            onNavigateToDetail = {}
        )
    }
}
