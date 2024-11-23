package com.example.gourmetsearchercompose.ui.screen.seachlocation.component

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
            LocationSearchState.LOADING -> LoadingContent()

            LocationSearchState.ERROR -> ErrorContent(
                errorMessage = getErrorMessage(locationPermissionsState.status.isGranted),
                onRetry = {
                    handleRetry(
                        locationPermissionsState.status.isGranted,
                        onRetry,
                        locationPermissionsState::launchPermissionRequest
                    )
                },
                onOpenSettings = onOpenSettings
            )

            LocationSearchState.RATIONAL_REQUIRED -> RationalDialog(
                locationPermissionsState::launchPermissionRequest
            )
        }
    }
}

/**
 * エラーメッセージ取得
 * @param isGranted パーミッションが許可されているか
 */
private fun getErrorMessage(isGranted: Boolean) = when (isGranted) {
    true -> R.string.search_location_error_message
    false -> R.string.search_location_permission_denied_message
}

/**
 * リトライ処理
 * @param isGranted パーミッションが許可されているか
 * @param onRetry リトライボタンクリック時のコールバック
 * @param launchPermissionRequest パーミッションリクエスト実行コールバック
 */
private fun handleRetry(
    isGranted: Boolean,
    onRetry: () -> Unit,
    launchPermissionRequest: () -> Unit
) = if (isGranted) onRetry() else launchPermissionRequest()