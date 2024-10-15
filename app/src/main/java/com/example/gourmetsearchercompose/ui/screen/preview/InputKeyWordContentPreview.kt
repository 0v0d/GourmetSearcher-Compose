package com.example.gourmetsearchercompose.ui.screen.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmetsearchercompose.theme.AppTheme
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.InputKeyWordContent
import kotlinx.collections.immutable.toImmutableList

private val testHistory = listOf("居酒屋", "カフェ", "ラーメン").toImmutableList()
private const val TEST_KEYWORD = "sushi"

/** キーワード入力画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun InputKeyWordContentKeyWordListPreview() {
    InputKeyWordContent(
        focusRequester = FocusRequester(),
        inputText = "",
        isInputEmpty = true,
        historyList = testHistory,
        onInputTextChange = {},
        onClearHistory = {},
        onRangeSelect = {}
    )
}

/** キーワード入力画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun InputKeyWordContentKeyWordListDarkPreview() {
    AppTheme(darkTheme = true) {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = "",
            isInputEmpty = true,
            historyList = testHistory,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}

/** キーワード入力画面プレビュー */
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun InputKeyWordContentRangeListPreview() {
    InputKeyWordContent(
        focusRequester = FocusRequester(),
        inputText = TEST_KEYWORD,
        isInputEmpty = false,
        historyList = testHistory,
        onInputTextChange = {},
        onClearHistory = {},
        onRangeSelect = {}
    )
}

/** キーワード入力画面ダークモードプレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun InputKeyWordContentRangeListDarkPreview() {
    AppTheme(darkTheme = true) {
        InputKeyWordContent(
            focusRequester = FocusRequester(),
            inputText = TEST_KEYWORD,
            isInputEmpty = false,
            historyList = testHistory,
            onInputTextChange = {},
            onClearHistory = {},
            onRangeSelect = {}
        )
    }
}
