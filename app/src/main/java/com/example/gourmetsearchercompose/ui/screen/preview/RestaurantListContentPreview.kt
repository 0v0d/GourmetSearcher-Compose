package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.model.api.BudgetData
import com.example.gourmetsearchercompose.model.api.GenreData
import com.example.gourmetsearchercompose.model.api.LargeAreaData
import com.example.gourmetsearchercompose.model.api.PCData
import com.example.gourmetsearchercompose.model.api.PhotoData
import com.example.gourmetsearchercompose.model.api.Shops
import com.example.gourmetsearchercompose.model.api.SmallAreaData
import com.example.gourmetsearchercompose.model.api.Urls
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.model.domain.toDomain
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.RestaurantListContent
import kotlinx.collections.immutable.toImmutableList

private val restaurantData = Shops(
    "炭火焼鳥 串カツ お好み焼き 肉寿司 食べ飲み放題 個室居酒屋 あたぼうや 難波心斎橋",
    "大阪府大阪市中央区難波１ - 7 - 16 現代こいさんビル3階",
    "なんば",
    LargeAreaData("大阪"),
    SmallAreaData("大阪難波"),
    GenreData("居酒屋"),
    BudgetData("2001～3000 円"),
    "なんば駅徒歩1分/2980円(税込)200品豪華食飲放題!!",
    Urls("\"https://www.hotpepper.jp/strJ003785192/?vos=nhppalsa000016"),
    PhotoData(PCData(l = "https://imgfp.hotp.jp/IMGH/41/50/P044314150/P044314150_238.jpg")),
    "月～金、祝前日: O～23:00 （料理L.O. 22:00 ドリンクL.O. 22:30",
    "年中無休!!",
)

private val emptyRestaurantList = emptyList<ShopsDomain>().toImmutableList()

/** レストランリスト画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenSuccessPreview() {
    val restaurantList = listOf(restaurantData.toDomain()).toImmutableList()
    RestaurantListContent(
        onClick = {},
        onRetry = {},
        searchState = SearchState.Success,
        shops = restaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenErrorPreview() {
    RestaurantListContent(
        onClick = {},
        onRetry = {},
        searchState = SearchState.NetworkError,
        shops = emptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面ローディングプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenLoadingPreview() {
    RestaurantListContent(
        onClick = {},
        onRetry = {},
        searchState = SearchState.Loading,
        shops = emptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面結果がなかった時のプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenEmptyPreview() {
    RestaurantListContent(
        onClick = {},
        onRetry = {},
        searchState = SearchState.EmptyResult,
        shops = emptyRestaurantList,
        onNavigateToDetail = {}
    )
}

/** レストランリスト画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantListScreenSuccessDarkPreview() {
    val restaurantList = listOf(restaurantData.toDomain()).toImmutableList()
    AppTheme(darkTheme = true) {
        RestaurantListContent(
            onClick = {},
            onRetry = {},
            searchState = SearchState.Success,
            shops = restaurantList,
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
            onClick = {},
            onRetry = {},
            searchState = SearchState.NetworkError,
            shops = emptyRestaurantList,
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
            onClick = {},
            onRetry = {},
            searchState = SearchState.Loading,
            shops = emptyRestaurantList,
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
            onClick = {},
            onRetry = {},
            searchState = SearchState.EmptyResult,
            shops = emptyRestaurantList,
            onNavigateToDetail = {}
        )
    }
}
