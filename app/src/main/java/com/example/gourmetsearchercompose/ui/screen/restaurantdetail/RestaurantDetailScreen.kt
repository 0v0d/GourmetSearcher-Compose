package com.example.gourmetsearchercompose.ui.screen.restaurantdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper
import com.example.gourmetsearchercompose.ui.screen.restaurantdetail.component.RestaurantDetailContent
import com.example.gourmetsearchercompose.utils.openMap
import com.example.gourmetsearchercompose.utils.openWebBrowser
import com.example.gourmetsearchercompose.viewmodel.RestaurantDetailViewModel

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
