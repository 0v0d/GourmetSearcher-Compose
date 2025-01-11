package com.example.feature_location.sample

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.example.shared_ui.previewutils.PreviewWrapper
import com.example.feature_location.component.SearchLocationContent
import com.example.feature_location.mock.FakePermissionState
import com.example.feature_location.state.LocationSearchState
import com.example.shared_ui.previewutils.isDarkTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus

/** 位置情報検索画面プレビュー */
@Suppress("UnusedPrivateMember")
@OptIn(ExperimentalPermissionsApi::class)
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoadingPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ContentErrorPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun DeniedErrorPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun RationalRequiredPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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
