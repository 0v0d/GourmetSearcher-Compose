package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.mock.MockRestaurantData.mockEmptyRestaurantList
import com.example.gourmetsearchercompose.mock.MockRestaurantData.mockRestaurantList
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantListContent
import kotlinx.collections.immutable.ImmutableList

@Composable
fun RestaurantListContentWrapper(
    searchState: SearchState,
    shops: ImmutableList<ShopsDomain> = mockEmptyRestaurantList
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
@Preview
@Composable
private fun RestaurantListScreenErrorPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.NetworkError
        )
    }
}

/** レストランリスト画面ローディングプレビュー */
@Preview
@Composable
private fun RestaurantListScreenLoadingPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.Loading
        )
    }
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Preview
@Composable
private fun RestaurantListScreenEmptyPreview() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.EmptyResult
        )
    }
}

/** レストランリスト画面ダークモードプレビュー */
@Preview
@Composable
private fun RestaurantListScreenSuccessDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.Success,
            shops = mockRestaurantList
        )
    }
}

/** レストランリスト画面エラープレビュー */
@Preview
@Composable
private fun RestaurantListScreenErrorDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.NetworkError
        )
    }
}

/** レストランリスト画面ローディングプレビュー */
@Preview
@Composable
private fun RestaurantListScreenLoadingDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.Loading
        )
    }
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Preview
@Composable
private fun RestaurantListScreenEmptyDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        RestaurantListContentWrapper(
            searchState = SearchState.EmptyResult
        )
    }
}