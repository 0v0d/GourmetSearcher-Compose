package com.example.feature_restaurant.restaurantdetail

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shared_ui.utils.PreviewWrapper
import com.example.feature_restaurant.R
import com.example.feature_restaurant.domain.ShopsDomain
import com.example.feature_restaurant.mock.MockRestaurantData.sampleRestaurantList
import com.example.feature_restaurant.restaurantdetail.component.RestaurantDetailContent
import com.example.feature_restaurant.viewmodel.RestaurantDetailViewModel

/**
 * レストラン詳細画面
 * @param restaurantData レストランデータ
 * @param modifier Modifier
 * @param viewModel RestaurantDetailViewModel
 */
@Composable
fun RestaurantDetailScreen(
    restaurantData: ShopsDomain,
    modifier: Modifier = Modifier,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val address by viewModel.address.collectAsState()
    val url by viewModel.url.collectAsState()

    /** ボタンが押されたらマップを開く */
    LaunchedEffect(address) {
        address?.let {
            openMap(context, it)
            viewModel.clearAddress()
        }
    }

    /** ボタンが押されたらWebブラウザを開く  */
    LaunchedEffect(url) {
        url?.let {
            openWebBrowser(context, it)
            viewModel.clearUrl()
        }
    }

    RestaurantDetailContent(
        restaurantData = restaurantData,
        onHotPepperClick = { viewModel.openUrl(restaurantData.url.pc) },
        onMapClick = { viewModel.openMap(restaurantData.address) },
        modifier = modifier
    )
}

/**
 * Webブラウザを開く
 * @param context コンテキスト
 * @param url URL
 */
private fun openWebBrowser(
    context: Context,
    url: String
) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.component = ComponentName(
        context.getString(R.string.restaurant_detail_chrome),
        context.getString(R.string.restaurant_detail_chrome_activity)
    )
    context.startActivity(intent)
}

/**
 * Google Mapを開く
 * @param context コンテキスト
 * @param address 住所
 */
private fun openMap(
    context: Context,
    address: String
) {
    val mapUrl = context.getString(R.string.restaurant_detail_map_url) + address
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
    intent.component = ComponentName(
        context.getString(R.string.restaurant_detail_google_map),
        context.getString(R.string.restaurant_detail_google_map_activity)
    )
    context.startActivity(intent)
}

/** レストラン詳細画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RestaurantDetailScreenPreview() {
    PreviewWrapper {
        RestaurantDetailScreen(
            restaurantData = sampleRestaurantList.first()
        )
    }
}
