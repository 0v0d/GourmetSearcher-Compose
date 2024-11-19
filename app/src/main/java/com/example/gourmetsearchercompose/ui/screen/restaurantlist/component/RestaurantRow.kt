package com.example.gourmetsearchercompose.ui.screen.restaurantlist.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.mock.MockRestaurantData
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
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

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewRestaurantRow() {
    RestaurantRow(
        shops = MockRestaurantData.sampleRestaurantList,
        navigateToDetail = {}
    )
}
