package com.example.feature_restaurant.restaurantdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList

/**
 * レストラン主要情報
 * @param restaurantData レストランデータ
 */
@Composable
fun RestaurantMainInfo(
    restaurantData: ShopsDomain,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            horizontal = 8.dp,
            vertical = 4.dp
        )
    ) {
        Text(
            text = restaurantData.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                DetailChip(
                    icon = Icons.Default.LocalDining,
                    text = restaurantData.genre.name
                )
                Spacer(modifier = Modifier.height(8.dp))
                DetailChip(
                    icon = Icons.Default.AttachMoney,
                    text = restaurantData.budget.name
                )
            }
            DetailChip(
                icon = Icons.Default.Schedule,
                text = restaurantData.open
            )
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewRestaurantMainInfo() {
    RestaurantMainInfo(
        restaurantData = sampleRestaurantList.first()
    )
}
