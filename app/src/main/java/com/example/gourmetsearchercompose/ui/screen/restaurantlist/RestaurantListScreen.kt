package com.example.gourmetsearchercompose.ui.screen.restaurantlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gourmetsearchercompose.model.data.SearchTerms
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.mock.MockRestaurantData
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.preview.RestaurantListContentWrapper
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantlist.component.RestaurantListContent
import com.example.gourmetsearchercompose.viewmodel.RestaurantListViewModel

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
