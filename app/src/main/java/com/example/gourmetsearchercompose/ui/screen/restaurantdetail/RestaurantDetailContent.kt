package com.example.gourmetsearchercompose.ui.screen.restaurantdetail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.ui.screen.component.ImageCard
import com.example.gourmetsearchercompose.ui.screen.component.IconText

/**
 * レストラン詳細画面コンテンツ
 * @param restaurantData レストランデータ
 * @param onHotPepperClick ホットペッパーボタンクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun RestaurantDetailContent(
    restaurantData: ShopsDomain,
    onHotPepperClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        // レストラン画像
        ImageCard(imageUrl = restaurantData.photo.pc.l)
        // レストラン名と主要情報
        RestaurantMainInfo(restaurantData)
        // 詳細情報
        RestaurantDetailCard(restaurantData)
        // ホットペッパーボタン
        OutlinedButton(
            onClick = { onHotPepperClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(Icons.Default.Event, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.restaurant_detail_hot_pepper))
        }
    }
}

/**
 * レストラン主要情報
 * @param restaurantData レストランデータ
 */
@Composable
private fun RestaurantMainInfo(restaurantData: ShopsDomain) {
    Column(
        modifier = Modifier.padding(
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

/**
 * レストラン詳細カード
 * @param restaurantData レストランデータ
 */
@Composable
private fun RestaurantDetailCard(
    restaurantData: ShopsDomain
) {
    Card(
        modifier = Modifier
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
                title =R.string.restaurant_detail_access,
                content = restaurantData.access,
                icon = Icons.AutoMirrored.Filled.DirectionsWalk
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            ContentItem(
                title =R.string.restaurant_detail_closed_days,
                content = restaurantData.close,
                icon = Icons.Default.EventBusy
            )
        }
    }
}

/**
 * コンテンツアイテム
 * @param title タイトル
 * @param content 内容
 * @param icon アイコン
 * @param modifier Modifier
 */
@Composable
private fun ContentItem(
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

/**
 * チップ表示
 * @param icon アイコン
 * @param text テキスト
 * @param modifier Modifier
 */
@Composable
private fun DetailChip(
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
