package com.example.gourmetsearchercompose.ui.screen.seachlocation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.state.LocationSearchState
import com.example.gourmetsearchercompose.ui.screen.component.Dialog
import com.example.gourmetsearchercompose.ui.screen.component.ErrorContent
import com.example.gourmetsearchercompose.ui.screen.component.LoadingContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

/**
 * 位置情報検索画面コンテンツ
 * @param searchState 位置情報検索状態
 * @param locationPermissionsState 位置情報パーミッション状態
 * @param onRetry リトライボタンクリック時のコールバック
 * @param onOpenSettings 設定画面遷移コールバック
 * @param modifier Modifier
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchLocationContent(
    searchState: LocationSearchState,
    locationPermissionsState: PermissionState,
    onRetry: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
