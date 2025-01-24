package com.example.feature_restaurant.restaurantlist.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import kotlinx.collections.immutable.ImmutableList

/**
 * レストランリスト
 * @param shops レストランリスト
 * @param navigateToDetail レストラン詳細画面遷移コールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantRow(
    shops: ImmutableList<ShopsDomain>,
    navigateToDetail: (ShopsDomain) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(shops) { restaurant ->
            RestaurantItem(
                restaurant = restaurant,
                onClick = { navigateToDetail(restaurant) }
            )
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewRestaurantRow() {
    RestaurantRow(
        shops = sampleRestaurantList,
        navigateToDetail = {},
        listState = LazyListState()
    )
}
