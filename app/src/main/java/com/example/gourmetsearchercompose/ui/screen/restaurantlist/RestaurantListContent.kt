package com.example.gourmetsearchercompose.ui.screen.restaurantlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.state.SearchState
import com.example.gourmetsearchercompose.ui.screen.common.ErrorContent
import com.example.gourmetsearchercompose.ui.screen.common.LoadingContent
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
    searchState: SearchState,
    shops: ImmutableList<ShopsDomain>?,
    onNavigateToDetail: (ShopsDomain) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            RestaurantSearchResult(
                searchState = searchState,
                shops = shops,
                onNavigateToDetail = onNavigateToDetail,
                onRetry = onRetry
            )
        }
    }
}

/**
 * レストラン検索結果
 * @param searchState 検索状態
 * @param shops レストランリスト
 * @param onNavigateToDetail レストラン詳細画面遷移コールバック
 * @param onRetry リトライボタンクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantSearchResult(
    searchState: SearchState,
    shops: ImmutableList<ShopsDomain>?,
    onNavigateToDetail: (ShopsDomain) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (searchState) {
        SearchState.Loading -> LoadingContent()
        SearchState.Success -> shops?.let {
            RestaurantList(
                it,
                onNavigateToDetail
            )
        }

        SearchState.EmptyResult -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.restaurant_list_empty_result_message),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }

        SearchState.NetworkError -> ErrorContent(
            errorMessage = R.string.restaurant_list_network_error_message,
            onRetry = { onRetry() },
            onOpenSettings = {
            }
        )
    }
}

/**
 * レストランリスト
 * @param shops レストランリスト
 * @param navigateToDetail レストラン詳細画面遷移コールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantList(
    shops: ImmutableList<ShopsDomain>,
    navigateToDetail: (ShopsDomain) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(shops) { restaurant ->
            RestaurantItem(
                restaurant = restaurant,
                onClick = { navigateToDetail(restaurant) }
            )
        }
    }
}

/**
 * レストランアイテム
 * @param restaurant レストランデータ
 * @param onClick クリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantItem(
    restaurant: ShopsDomain,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(restaurant.photo.pc.l)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                RestaurantInfo(restaurant = restaurant)
            }
        }
    }
}

/**
 * レストラン情報
 * @param restaurant レストランデータ
 * @param modifier Modifier
 */
@Composable
fun RestaurantInfo(
    restaurant: ShopsDomain,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${restaurant.smallArea.name}[${restaurant.largeArea.name}]",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = restaurant.genre.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = restaurant.budget.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Row {
            Text(
                text = restaurant.access,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
