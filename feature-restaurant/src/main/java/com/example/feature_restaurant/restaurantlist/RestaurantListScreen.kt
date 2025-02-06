package com.example.feature_restaurant.restaurantlist

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature_restaurant.model.SearchTerms
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.restaurantlist.component.RestaurantListContent
import com.example.feature_restaurant.state.SearchState
import com.example.feature_restaurant.viewmodel.RestaurantListViewModel
import com.example.shared_ui.previewutils.PreviewWrapper
import com.example.shared_ui.previewutils.isDarkTheme
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_restaurant.preview.RestaurantListContentWrapper

/**
 * レストランリスト画面
 * @param searchTerms 検索条件
 * @param viewModel ViewModel
 * @param onNavigateToDetail レストラン詳細画面遷移コールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantListScreen(
    searchTerms: SearchTerms,
    onNavigateToDetail: (ShopsDomain) -> Unit,
    popBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RestaurantListViewModel = hiltViewModel()
) {
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    val pagingFlow = viewModel.shops.collectAsState().value
    val shops = pagingFlow?.collectAsLazyPagingItems()


    LaunchedEffect(Unit) {
        viewModel.searchRestaurants(searchTerms)
    }

    RestaurantListContent(
        onRetry = { viewModel.searchRestaurants(searchTerms) },
        searchState = searchState,
        shops = shops,
        onNavigateToDetail = onNavigateToDetail,
        popBack = { popBack() },
        modifier = modifier
    )
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PreviewRestaurantListContent() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
        RestaurantListContentWrapper(
            searchState = SearchState.SUCCESS,
        )
    }
}