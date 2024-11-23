package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.mock.MockSearchTerms.KEYWORD
import com.example.gourmetsearchercompose.mock.MockSearchTerms.keywordHistory
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.InputKeyWordContent
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper

/** キーワード入力画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun KeyWordListPreview() {
    PreviewWrapper {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = "",
            historyList = keywordHistory,
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
            historyList = keywordHistory,
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
            historyList = keywordHistory,
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
            historyList = keywordHistory,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}
