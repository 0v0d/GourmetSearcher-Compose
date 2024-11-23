package com.example.gourmetsearchercompose.ui.screen.inputkeyword.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory.KeyWordHistoryList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.range.RangeList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.textfield.SearchTextField
import kotlinx.collections.immutable.ImmutableList

/**
 * キーワード入力画面のコンテンツ
 * @param inputText 入力テキスト
 * @param historyList 履歴リスト
 * @param onInputTextChange 入力テキスト変更時のコールバック
 * @param onClearHistory 履歴クリアボタンクリック時のコールバック
 * @param onRangeSelect 範囲選択時のコールバック
 * @param modifier Modifier
 */
@Composable
fun InputKeyWordContent(
    inputText: String,
    focusRequester: FocusRequester,
    historyList: ImmutableList<String>,
    onInputTextChange: (String) -> Unit,
    onClearHistory: () -> Unit,
    onRangeSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            focusRequester = focusRequester,
            query = inputText,
            onQueryChange = onInputTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .testTag("SearchBar")
        )
        if (inputText.isEmpty()) {
            KeyWordHistoryList(
                historyList = historyList,
                onItemClick = { onInputTextChange(it) },
                onClearClick = onClearHistory
            )
        } else {
            RangeList(onRangeSelect = onRangeSelect)
        }
    }
}
