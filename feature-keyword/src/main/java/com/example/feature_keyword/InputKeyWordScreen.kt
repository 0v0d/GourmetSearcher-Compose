package com.example.feature_keyword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_keyword.component.InputKeyWordContent

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
