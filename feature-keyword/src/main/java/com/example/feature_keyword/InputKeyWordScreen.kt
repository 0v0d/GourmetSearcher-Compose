package com.example.feature_keyword

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_keyword.component.InputKeyWordContent
import com.example.feature_keyword.mock.MockKeyword.KEYWORD
import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList
import com.example.feature_keyword.viewmodel.InputKeyWordViewModel
import com.example.shared_ui.previewutils.PreviewWrapper
import com.example.shared_ui.previewutils.isDarkTheme

/**
 * キーワード入力画面
 * @param viewModel ViewModel
 * @param onNavigateToSearchLocation 検索画面へ遷移するコールバック
 */
@Composable
fun InputKeyWordScreen(
    viewModel: InputKeyWordViewModel = hiltViewModel(),
    onNavigateToSearchLocation: (String, Int) -> Unit,
) {
    val inputText = viewModel.inputText.value
    val historyList by viewModel.historyListData.collectAsState()
    val focusRequester = remember { FocusRequester() }

    InputKeyWordContent(
        focusRequester = focusRequester,
        inputText = inputText,
        historyList = historyList,
        onInputTextChange = { viewModel.updateInputText(it) },
        onClearHistory = { viewModel.clearHistory() },
        onRangeSelect = {
            onNavigateToSearchLocation(inputText, it)
            viewModel.saveHistoryItem(inputText)
            viewModel.updateInputText("")
        }
    )
}

/** キーワード入力画面プレビュー */
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
fun KeyWordListPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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
private fun RangeListPreview() {
    val mode = isDarkTheme(LocalConfiguration.current.uiMode)
    PreviewWrapper(darkTheme = mode) {
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