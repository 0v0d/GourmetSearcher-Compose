package com.example.gourmetsearchercompose.ui.screen.restaurantdetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.ui.screen.component.CustomOutlinedButton
import com.example.gourmetsearchercompose.ui.screen.component.ImageCard
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.RestaurantDetailScreen

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
    onMapClick: () -> Unit,
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

        // マップボタン
        CustomOutlinedButton(
            text = R.string.restaurant_detail_map_description,
            onClick = { onMapClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            icon = Icons.Default.Place
        )

        // ホットペッパーボタン
        CustomOutlinedButton(
            text = R.string.restaurant_detail_hot_pepper,
            onClick = { onHotPepperClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            icon = Icons.Default.Event
        )
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantDetailContentPreview() {
    PreviewWrapper {
        RestaurantDetailScreen(
            restaurantData = sampleRestaurantList[0]
        )
    }
}
