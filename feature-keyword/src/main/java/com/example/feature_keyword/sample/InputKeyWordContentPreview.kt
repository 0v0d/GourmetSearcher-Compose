package com.example.feature_keyword.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.PreviewWrapper
import com.example.feature_keyword.component.InputKeyWordContent
import com.example.feature_keyword.mock.MockKeyword.KEYWORD
import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList

/** キーワード入力画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun KeyWordListPreview() {
    PreviewWrapper {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = "",
            historyList = sampleHistoryList,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}

/** キーワード入力画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun KeyWordListDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = "",
            historyList = sampleHistoryList,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}

/** キーワード入力画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RangeListPreview() {
    PreviewWrapper {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = KEYWORD,
            historyList = sampleHistoryList,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}

/** キーワード入力画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun RangeListDarkPreview() {
    PreviewWrapper(darkTheme = true) {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = KEYWORD,
            historyList = sampleHistoryList,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}
