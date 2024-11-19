package com.example.gourmetsearchercompose.ui.screen.restaurantlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.mock.MockRestaurantData
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.ui.screen.component.ImageCard

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            ImageCard(
                imageUrl = restaurant.photo.pc.l,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            RestaurantInfo(restaurant = restaurant)
        }
    }
}

@Preview
@Composable
private fun PreviewRestaurantItem() {
    RestaurantItem(
        restaurant = MockRestaurantData.mockRestaurantData,
        onClick = {}
    )
}
