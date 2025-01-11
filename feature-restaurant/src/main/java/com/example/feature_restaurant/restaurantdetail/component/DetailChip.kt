package com.example.feature_restaurant.restaurantdetail.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.shared_ui.IconText

/**
 * チップ表示
 * @param icon アイコン
 * @param text テキスト
 * @param modifier Modifier
 */
@Composable
fun DetailChip(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = colorScheme.surfaceVariant
    ) {
        IconText(
            icon = icon,
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewDetailChip() {
    DetailChip(
        icon = Icons.Default.AttachMoney,
        text = sampleRestaurantList.first().budget.name
    )
}
