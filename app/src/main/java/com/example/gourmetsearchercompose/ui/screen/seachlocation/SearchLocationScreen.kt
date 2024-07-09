package com.example.gourmetsearchercompose.ui.screen.seachlocation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gourmetsearchercompose.model.data.CurrentLocation
import com.example.gourmetsearchercompose.model.data.SearchTerms
import com.example.gourmetsearchercompose.utils.openSettings
import com.example.gourmetsearchercompose.viewmodel.SearchLocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 * 位置情報検索画面
 * @param onClick 戻るボタンクリック時のコールバック
 * @param inputText 検索テキスト
 * @param range 検索範囲
 * @param modifier Modifier
 * @param viewModel ViewModel
 * @param onNavigateToResultList 検索結果画面遷移コールバック
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchLocationScreen(
    onClick: () -> Unit,
    inputText: String,
    range: Int,
    modifier: Modifier = Modifier,
    viewModel: SearchLocationViewModel = hiltViewModel(),
    onNavigateToResultList: (SearchTerms) -> Unit,
) {
    val context = LocalContext.current
    val searchState by viewModel.locationSearchState.collectAsStateWithLifecycle()
    val locationData by viewModel.locationData.collectAsStateWithLifecycle()
    val locationPermissionsState =
        rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION)

    HandlePermissionEffects(
        locationPermissionsState = locationPermissionsState,
        onPermissionGrant = viewModel::handlePermissionGranted,
        onRationaleRequire = viewModel::handleRationaleRequired,
        onPermissionDeny = viewModel::handlePermissionDenied
    )

    HandleLocationDataEffect(locationData, inputText, range, onNavigateToResultList)

    val rememberedOnNavigateToResultList by rememberUpdatedState(onNavigateToResultList)

    LaunchedEffect(locationData) {
        locationData?.let { location ->
            rememberedOnNavigateToResultList(SearchTerms(inputText, location, range))
        }
    }

    SearchLocationContent(
        modifier = modifier,
        onClick = onClick,
        searchState = searchState,
        locationPermissionsState = locationPermissionsState,
        onRetry = viewModel::retryLocationRequest,
        onOpenSettings = {
            openSettings(context = context)
        }
    )
}

/**
 * 位置情報検索画面コンテンツ
 * @param locationPermissionsState 位置情報パーミッション状態
 * @param onPermissionGrant 位置情報パーミッション許可時のコールバック
 * @param onRationaleRequire 位置情報パーミッション許可が必要な旨のコールバック
 * @param onPermissionDeny 位置情報パーミッション拒否時のコールバック
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HandlePermissionEffects(
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

@Composable
private fun HandleLocationDataEffect(
    locationData: CurrentLocation?,
    inputText: String,
    range: Int,
    onNavigateToResultList: (SearchTerms) -> Unit
) {
    val rememberedOnNavigateToResultList by rememberUpdatedState(onNavigateToResultList)

    LaunchedEffect(locationData, inputText, range) {
        locationData?.let { location ->
            rememberedOnNavigateToResultList(SearchTerms(inputText, location, range))
        }
    }
}
