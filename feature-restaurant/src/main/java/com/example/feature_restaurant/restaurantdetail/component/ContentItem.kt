package com.example.feature_restaurant.restaurantdetail.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_restaurant.R
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList

/**
 * コンテンツアイテム
 * @param title タイトル
 * @param content 内容
 * @param icon アイコン
 * @param modifier Modifier
 */
@Composable
fun ContentItem(
    @StringRes title: Int,
    content: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = stringResource(title), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = content, fontSize = 14.sp)
            }
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewContentItem() {
    ContentItem(
        title = R.string.restaurant_detail_closed_days,
        content = sampleRestaurantList.first().close,
        icon = Icons.Default.EventBusy
    )
}
