package com.example.gourmetsearchercompose.ui.screen.seachlocation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gourmetsearchercompose.model.data.SearchTerms
import com.example.gourmetsearchercompose.ui.screen.seachlocation.component.SearchLocationContent
import com.example.gourmetsearchercompose.ui.screen.seachlocation.effect.HandleLocationDataEffect
import com.example.gourmetsearchercompose.ui.screen.seachlocation.effect.HandlePermissionEffects
import com.example.gourmetsearchercompose.utils.openSettings
import com.example.gourmetsearchercompose.viewmodel.SearchLocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

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
    onNavigateToResultList: (SearchTerms) -> Unit,
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
            searchTerms =
            SearchTerms(
                keyword = inputText,
                location = it,
                range = range
            ),
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
