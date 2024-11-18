package com.example.gourmetsearchercompose.ui.screen.inputkeyword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import kotlinx.collections.immutable.ImmutableList

/**
 * キーワード入力画面のコンテンツ
 * @param inputText 入力テキスト
 * @param isInputEmpty 入力テキストが空かどうか
 * @param historyList 履歴リスト
 * @param onInputTextChange 入力テキスト変更時のコールバック
 * @param onClearHistory 履歴クリアボタンクリック時のコールバック
 * @param onRangeSelect 範囲選択時のコールバック
 * @param modifier Modifier
 */
@Composable
fun InputKeyWordContent(
    inputText: String,
    isInputEmpty: Boolean,
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
        horizontalAlignment = Alignment.CenterHorizontally,
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
                .testTag("SearchBar"),
        )
        if (isInputEmpty) {
            KeyWordHistoryList(
                historyList = historyList,
                onItemClick = { onInputTextChange(it) },
                onClearClick = onClearHistory,
            )
        } else {
            RangeList(
                ranges = stringArrayResource(R.array.input_keyword_range_array),
                onRangeSelect = onRangeSelect,
            )
        }
    }
}
