package com.example.feature_location

import android.Manifest
import android.content.Context
import android.content.Intent
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.model.data.CurrentLocation
import com.example.feature_location.component.SearchLocationContent
import com.example.feature_location.effect.HandleLocationDataEffect
import com.example.feature_location.effect.HandlePermissionEffects
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.example.feature_location.viewmodel.SearchLocationViewModel

/**
 * 位置情報検索画面
 * @param inputText 検索テキスト
 * @param range 検索範囲
 * @param viewModel ViewModel
 * @param onNavigateToResultList 検索結果画面遷移コールバック
 * @param modifier Modifier
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchLocationScreen(
    inputText: String,
    range: Int,
    onNavigateToResultList: (
            inputText: String,
            range: Int,
            latitude: Double,
            longitude: Double
            ) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchLocationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val searchState by viewModel.locationSearchState.collectAsStateWithLifecycle()
    val locationData by viewModel.locationData.collectAsStateWithLifecycle()
    val locationPermissionsState =
        rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION)

    // 位置情報パーミッションの状態によって処理を分岐
    HandlePermissionEffects(
        locationPermissionsState = locationPermissionsState,
        onPermissionGrant = viewModel::handlePermissionGranted,
        onRationaleRequire = viewModel::handleRationaleRequired,
        onPermissionDeny = viewModel::handlePermissionDenied
    )

    // 位置情報が取得できたら検索結果画面に遷移
    locationData?.let {
        HandleLocationDataEffect(
            keyword = inputText,
            location = CurrentLocation(
                latitude = it.latitude,
                longitude = it.longitude
            ),
            range = range,
            onNavigateToResultList = onNavigateToResultList
        )
    }

    SearchLocationContent(
        modifier = modifier,
        searchState = searchState,
        locationPermissionsState = locationPermissionsState,
        onRetry = viewModel::retryLocationRequest,
        onOpenSettings = {
            openSettings(context = context)
        }
    )
}

/**
 *  設定画面を開く
 *  @param context コンテキスト
 */
private fun openSettings(context: Context) {
    val intent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}
