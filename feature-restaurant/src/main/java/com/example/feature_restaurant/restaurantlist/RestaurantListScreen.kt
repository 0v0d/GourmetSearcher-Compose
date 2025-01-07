package com.example.feature_restaurant.restaurantlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature_restaurant.SearchTerms
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.restaurantlist.component.RestaurantListContent
import com.example.feature_restaurant.viewmodel.RestaurantListViewModel

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
    val shops by viewModel.shops.collectAsStateWithLifecycle()

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
