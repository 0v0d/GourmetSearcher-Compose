package com.example.gourmetsearchercompose.ui.screen.restaurantlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gourmetsearchercompose.model.data.SearchTerms
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.viewmodel.RestaurantListViewModel

/**
 * レストランリスト画面
 * @param onClick 戻るボタンクリック時のコールバック
 * @param searchTerms 検索条件
 * @param modifier Modifier
 * @param viewModel ViewModel
 * @param onNavigateToDetail レストラン詳細画面遷移コールバック
 */
@Composable
fun RestaurantListScreen(
    onClick: () -> Unit,
    searchTerms: SearchTerms,
    modifier: Modifier = Modifier,
    viewModel: RestaurantListViewModel = hiltViewModel(),
    onNavigateToDetail: (ShopsDomain) -> Unit
) {
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    val shops by viewModel.shops.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.searchRestaurants(searchTerms)
    }

    RestaurantListContent(
        modifier = modifier,
        onClick = onClick,
        onRetry = { viewModel.searchRestaurants(searchTerms) },
        searchState = searchState,
        shops = shops,
        onNavigateToDetail = onNavigateToDetail
    )
}
