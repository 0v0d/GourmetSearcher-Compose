package com.example.gourmetsearchercompose.ui.screen.seachlocation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.ui.screen.component.Dialog
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewComponentWrapper

/**
 * パーミッションリクエストダイアログ
 * @param launchPermissionRequest パーミッションリクエスト実行
 */
@Composable
fun RationalDialog(
    launchPermissionRequest: () -> Unit
) {
    Dialog(
        onConfirmClick = { launchPermissionRequest() },
        errorMessage = R.string.search_location_permission_required_message
    )
}

/** 位置情報検索の許可が必要な旨のダイアログプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RationaleContentPreview() {
    PreviewComponentWrapper {
        RationalDialog(launchPermissionRequest = {})
    }
}

/** 位置情報検索の許可が必要な旨のダイアログダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RationaleContentDarkPreview() {
    PreviewComponentWrapper(darkTheme = true) {
        RationalDialog(launchPermissionRequest = {})
    }
}
