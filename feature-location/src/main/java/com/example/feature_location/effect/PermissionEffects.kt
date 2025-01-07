package com.example.feature_location.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

/**
 * 位置情報検索画面コンテンツ
 * @param locationPermissionsState 位置情報パーミッション状態
 * @param onPermissionGrant 位置情報パーミッション許可時のコールバック
 * @param onRationaleRequire 位置情報パーミッション許可が必要な旨のコールバック
 * @param onPermissionDeny 位置情報パーミッション拒否時のコールバック
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HandlePermissionEffects(
    locationPermissionsState: PermissionState,
    onPermissionGrant: () -> Unit,
    onRationaleRequire: () -> Unit,
    onPermissionDeny: () -> Unit
) {
    val rememberedOnPermissionGranted by rememberUpdatedState(onPermissionGrant)
    val rememberedOnRationaleRequired by rememberUpdatedState(onRationaleRequire)
    val rememberedOnPermissionDenied by rememberUpdatedState(onPermissionDeny)

    LaunchedEffect(Unit) {
        locationPermissionsState.launchPermissionRequest()
    }

    LaunchedEffect(locationPermissionsState.status) {
        when {
            locationPermissionsState.status.isGranted -> rememberedOnPermissionGranted()
            locationPermissionsState.status.shouldShowRationale -> rememberedOnRationaleRequired()
            else -> rememberedOnPermissionDenied()
        }
    }
}
