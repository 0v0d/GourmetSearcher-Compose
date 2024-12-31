package com.example.gourmetsearchercompose.ui.screen.restaurantdetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewComponentWrapper

/**
 * レストラン詳細カード
 * @param restaurantData レストランデータ
 */
@Composable
fun RestaurantDetailCard(
    restaurantData: ShopsDomain,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            ContentItem(
                title = R.string.restaurant_detail_address,
                content = restaurantData.address,
                icon = Icons.Default.LocationOn
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            ContentItem(
                title = R.string.restaurant_detail_access,
                content = restaurantData.access,
                icon = Icons.AutoMirrored.Filled.DirectionsWalk
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            ContentItem(
                title = R.string.restaurant_detail_closed_days,
                content = restaurantData.close,
                icon = Icons.Default.EventBusy
            )
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewRestaurantDetailCard() {
    PreviewComponentWrapper {
        RestaurantDetailCard(
            restaurantData = sampleRestaurantList[0]
        )
    }
}
