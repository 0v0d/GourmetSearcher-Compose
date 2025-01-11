package com.example.feature_restaurant.restaurantlist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.shared_ui.IconText

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
        IconText(
            icon = Icons.Default.LocationOn,
            text = "${restaurant.smallArea.name}[${restaurant.largeArea.name}]",
        )
        IconText(
            icon = Icons.Default.LocalDining,
            text = restaurant.genre.name,
        )
        IconText(
            icon = Icons.Default.AttachMoney,
            text = restaurant.budget.name
        )
        IconText(
            icon = Icons.AutoMirrored.Filled.DirectionsWalk,
            text = restaurant.access
        )
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewRestaurantInfo() {
    RestaurantInfo(
        restaurant = sampleRestaurantList.first()
    )
}
