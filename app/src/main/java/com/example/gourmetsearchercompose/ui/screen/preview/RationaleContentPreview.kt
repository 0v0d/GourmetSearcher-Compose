package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.component.Dialog

/** 位置情報検索の許可が必要な旨のダイアログプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RationaleContentPreview() {
    Dialog(
        onConfirmClick = {},
        errorMessage = R.string.search_location_permission_required_message
    )
}

/** 位置情報検索の許可が必要な旨のダイアログダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RationaleContentDarkPreview() {
    AppTheme(darkTheme = true) {
        Dialog(
            onConfirmClick = {},
            errorMessage = R.string.search_location_permission_required_message
        )
    }
}
