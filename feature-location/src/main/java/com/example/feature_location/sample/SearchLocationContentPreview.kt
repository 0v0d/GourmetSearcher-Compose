package com.example.feature_location.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.PreviewWrapper
import com.example.feature_location.component.SearchLocationContent
import com.example.feature_location.mock.FakePermissionState
import com.example.feature_location.state.LocationSearchState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus

/** 位置情報検索画面プレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun LoadingPreview() {
    PreviewWrapper {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.LOADING,
            locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun ContentErrorPreview() {
    PreviewWrapper {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.ERROR,
            locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面拒否エラープレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun DeniedErrorPreview() {
    PreviewWrapper {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.ERROR,
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

/** 位置情報検索画面許可ダイアログプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun RationalRequiredPreview() {
    PreviewWrapper {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.RATIONAL_REQUIRED,
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

/** 位置情報検索画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun SearchLocationContentLoadingDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.LOADING,
            locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面エラープレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun ErrorDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.ERROR,
            locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
            onRetry = {},
            onOpenSettings = {}
        )
    }
}

/** 位置情報検索画面拒否エラーダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun DeniedErrorDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.ERROR,
            locationPermissionsState = FakePermissionState(
                PermissionStatus.Denied(
                    shouldShowRationale = false
                )
            ),
            onRetry = {},
            onOpenSettings = {},
        )
    }
}

/** 位置情報検索画面許可ダイアログダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun RationalRequiredDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        SearchLocationContent(
            modifier = Modifier,
            searchState = LocationSearchState.RATIONAL_REQUIRED,
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
