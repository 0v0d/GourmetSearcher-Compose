package com.example.gourmetsearchercompose.ui.screen.inputkeyword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import kotlinx.collections.immutable.ImmutableList

/**
 * キーワード入力画面のコンテンツ
 * @param modifier Modifier
 * @param inputText 入力テキスト
 * @param isInputEmpty 入力テキストが空かどうか
 * @param historyList 履歴リスト
 * @param onInputTextChange 入力テキスト変更時のコールバック
 * @param onHistoryItemClick 履歴アイテムクリック時のコールバック
 * @param onClearHistory 履歴クリアボタンクリック時のコールバック
 * @param onRangeSelect 範囲選択時のコールバック
 */
@Composable
fun InputKeyWordContent(
    inputText: String,
    isInputEmpty: Boolean,
    focusRequester: FocusRequester,
    historyList: ImmutableList<String>,
    onInputTextChange: (String) -> Unit,
    onHistoryItemClick: (String) -> Unit,
    onClearHistory: () -> Unit,
    modifier: Modifier = Modifier,
    onRangeSelect: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { InputKeyWordTopAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchTextField(
                focusRequester = focusRequester,
                query = inputText,
                onQueryChange = onInputTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .testTag("SearchBar"),
            )
            if (isInputEmpty) {
                KeyWordHistoryList(
                    historyList = historyList,
                    onItemClick = onHistoryItemClick,
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
}

/**
 * トップバー
 * @param modifier Modifier
 */
@Composable
fun InputKeyWordTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.input_keyword_top_bar_title)) }
    )
}
