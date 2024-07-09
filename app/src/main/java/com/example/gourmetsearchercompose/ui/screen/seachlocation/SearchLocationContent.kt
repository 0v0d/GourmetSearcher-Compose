package com.example.gourmetsearchercompose.ui.screen.seachlocation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.state.LocationSearchState
import com.example.gourmetsearchercompose.ui.screen.common.AppBarContent
import com.example.gourmetsearchercompose.ui.screen.common.Dialog
import com.example.gourmetsearchercompose.ui.screen.common.ErrorContent
import com.example.gourmetsearchercompose.ui.screen.common.LoadingContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

/**
 * 位置情報検索画面コンテンツ
 * @param onClick 戻るボタンクリック時のコールバック
 * @param searchState 位置情報検索状態
 * @param locationPermissionsState 位置情報パーミッション状態
 * @param onRetry リトライボタンクリック時のコールバック
 * @param modifier Modifier
 * @param onOpenSettings 設定画面遷移コールバック
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchLocationContent(
    onClick: () -> Unit,
    searchState: LocationSearchState,
    locationPermissionsState: PermissionState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    onOpenSettings: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            SearchLocationTopBar(onClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (searchState) {
                LocationSearchState.Loading -> LoadingContent()
                LocationSearchState.Error -> ErrorContent(
                    errorMessage = if (locationPermissionsState.status.isGranted) {
                        R.string.search_location_error_message
                    } else {
                        R.string.search_location_permission_denied_message
                    },
                    onRetry = {
                        if (locationPermissionsState.status.isGranted) {
                            onRetry()
                        } else {
                            locationPermissionsState.launchPermissionRequest()
                        }
                    },
                    onOpenSettings = onOpenSettings
                )

                LocationSearchState.RationalRequired -> Dialog(
                    onConfirmClick = { locationPermissionsState.launchPermissionRequest() },
                    errorMessage = R.string.search_location_permission_required_message
                )
            }
        }
    }
}

/**
 * トップバー
 * @param onClick 戻るボタンクリック時のコールバック
 */
@Composable
private fun SearchLocationTopBar(onClick: () -> Unit) {
    AppBarContent(
        onClick = onClick,
        title = stringResource(id = R.string.search_location_top_bar_title),
    )
}
