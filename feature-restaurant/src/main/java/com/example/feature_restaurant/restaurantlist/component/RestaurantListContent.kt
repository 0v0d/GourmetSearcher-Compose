package com.example.feature_restaurant.restaurantlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.feature_restaurant.R
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.state.SearchState
import com.example.shared_ui.ErrorContent
import com.example.shared_ui.LoadingContent

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
    shops: LazyPagingItems<ShopsDomain>?,
    onNavigateToDetail: (ShopsDomain) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        when {
            // 成功時
            searchState == SearchState.SUCCESS && shops != null -> {
                RestaurantRow(
                    shops = shops,
                    navigateToDetail = onNavigateToDetail,
                    listState = listState
                )
            }

            // 検索結果が空
            searchState == SearchState.EMPTY_RESULT -> ErrorContent(
                errorMessage = R.string.restaurant_list_empty_result_message,
                onRetry = { popBack() },
                buttonText = R.string.restaurant_list_retry_keyword,
                isSettingButtonEnabled = false,
            )

            // ネットワークエラー
            searchState == SearchState.NETWORK_ERROR -> ErrorContent(
                errorMessage = R.string.restaurant_list_network_error_message,
                onRetry = { onRetry() },
                isSettingButtonEnabled = false,
            )

            else -> LoadingContent()
        }
    }
}
