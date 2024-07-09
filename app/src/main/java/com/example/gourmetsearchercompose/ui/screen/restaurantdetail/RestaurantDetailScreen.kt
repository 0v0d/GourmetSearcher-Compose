package com.example.gourmetsearchercompose.ui.screen.restaurantdetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.model.domain.ShopsDomain
import com.example.gourmetsearchercompose.utils.openMap
import com.example.gourmetsearchercompose.utils.openWebBrowser
import com.example.gourmetsearchercompose.viewmodel.RestaurantDetailViewModel

/**
 * レストラン詳細画面
 * @param onClick 戻るボタンクリック時のコールバック
 * @param restaurantData レストランデータ
 * @param modifier Modifier
 * @param viewModel RestaurantDetailViewModel
 */
@Composable
fun RestaurantDetailScreen(
    onClick: () -> Unit,
    restaurantData: ShopsDomain,
    modifier: Modifier = Modifier,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val address by viewModel.address.collectAsState()
    val url by viewModel.url.collectAsState()

    LaunchedEffect(address) {
        address?.let {
            openMap(context, it)
            viewModel.clearAddress()
        }
    }

    LaunchedEffect(url) {
        url?.let {
            openWebBrowser(context, it)
            viewModel.clearUrl()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            RestaurantDetailTopBar(
                title = restaurantData.name,
                onClick = onClick
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.openUrl(restaurantData.address + restaurantData.name)
                },
                icon = { Icon(Icons.Default.Explore, contentDescription = "Map") },
                text = { Text(stringResource(R.string.restaurant_detail_map_description)) },
                containerColor = colorScheme.surfaceVariant,
            )
        }
    ) { paddingValues ->
        RestaurantDetailContent(
            paddingValues = paddingValues,
            restaurantData = restaurantData,
            onHotPepperClick = { viewModel.openUrl(restaurantData.url.pc) }
        )
    }
}
