package com.example.gourmetsearchercompose.ui.screen.preview

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.state.LocationSearchState
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.seachlocation.SearchLocationContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

/** 位置情報検索画面プレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentLoadingPreview() {
    SearchLocationContent(
        onClick = {},
        searchState = LocationSearchState.Loading,
        locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
        onRetry = {},
        onOpenSettings = {}
    )
}

/** 位置情報検索画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentErrorPreview() {
    SearchLocationContent(
        onClick = {},
        searchState = LocationSearchState.Error,
        locationPermissionsState = FakePermissionState(PermissionStatus.Denied(shouldShowRationale = false)),
        onRetry = {},
        onOpenSettings = {}
    )
}

/** 位置情報検索画面許可ダイアログプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentRationalRequiredPreview() {
    SearchLocationContent(
        onClick = {},
        searchState = LocationSearchState.RationalRequired,
        locationPermissionsState = FakePermissionState(PermissionStatus.Denied(shouldShowRationale = true)),
        onRetry = {},
        onOpenSettings = {}
    )
}

/** 位置情報検索画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentLoadingDarkPreview() {
    AppTheme(darkTheme = true) {
        SearchLocationContent(
            onClick = {},
            searchState = LocationSearchState.Loading,
            locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面エラーダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentErrorDarkPreview() {
    AppTheme(darkTheme = true) {
        SearchLocationContent(
            onClick = {},
            searchState = LocationSearchState.Error,
            locationPermissionsState = FakePermissionState(
                PermissionStatus.Denied(
                    shouldShowRationale = false
                )
            ),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面許可ダイアログダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchLocationContentRationalRequiredDarkPreview() {
    AppTheme(darkTheme = true) {
        SearchLocationContent(
            onClick = {},
            searchState = LocationSearchState.RationalRequired,
            locationPermissionsState = FakePermissionState(
                PermissionStatus.Denied(
                    shouldShowRationale = true
                )
            ),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/**
 * パーミッションステータスを返すダミークラス
 * @param initialStatus パーミッションステータス
 * @return パーミッションステータス
 */
@OptIn(ExperimentalPermissionsApi::class)
private class FakePermissionState(initialStatus: PermissionStatus) : PermissionState {
    override val permission: String = Manifest.permission.ACCESS_COARSE_LOCATION
    override var status: PermissionStatus = initialStatus

    @Suppress("EmptyFunctionBlock")
    override fun launchPermissionRequest() {
    }
}
