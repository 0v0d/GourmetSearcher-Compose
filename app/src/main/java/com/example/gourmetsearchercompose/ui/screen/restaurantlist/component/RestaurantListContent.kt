package com.example.gourmetsearchercompose.ui.screen.restaurantlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.mock.MockRestaurantData
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.component.ErrorContent
import com.example.gourmetsearchercompose.ui.screen.component.LoadingContent
import com.example.gourmetsearchercompose.ui.screen.preview.RestaurantListContentWrapper
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import kotlinx.collections.immutable.ImmutableList

/**
 * レストランリスト画面コンテンツ
 * @param onRetry リトライボタンクリック時のコールバック
 * @param searchState 検索状態
 * @param shops レストランリスト
 * @param onNavigateToDetail レストラン詳細画面遷移コールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantListContent(
    onRetry: () -> Unit,
    popBack: () -> Unit,
    searchState: SearchState,
    shops: ImmutableList<ShopsDomain>?,
    onNavigateToDetail: (ShopsDomain) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        when (searchState) {
            SearchState.Loading -> LoadingContent()

            SearchState.Success -> shops?.let {
                RestaurantRow(it, onNavigateToDetail)
            }

            SearchState.EmptyResult -> ErrorContent(
                errorMessage = R.string.restaurant_list_empty_result_message,
                onRetry = { popBack() },
                retryButtonText = R.string.restaurant_list_retry_keyword,
                isSettingButtonEnabled = false,
            )

            SearchState.NetworkError -> ErrorContent(
                errorMessage = R.string.restaurant_list_network_error_message,
                onRetry = { onRetry() },
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewRestaurantListContent() {
    PreviewWrapper {
        RestaurantListContentWrapper(
            searchState = SearchState.Success,
            shops = MockRestaurantData.sampleRestaurantList
        )
    }
}
