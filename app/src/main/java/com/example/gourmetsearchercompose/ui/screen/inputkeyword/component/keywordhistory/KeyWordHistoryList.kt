package com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleHistoryList
import com.example.gourmetsearchercompose.ui.screen.component.CustomOutlinedButton
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewComponentWrapper
import kotlinx.collections.immutable.ImmutableList

/**
 * キーワード履歴リスト
 * @param historyList 履歴リスト
 * @param onItemClick 履歴アイテムクリック時のコールバック
 * @param onClearClick 履歴クリアボタンクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun KeyWordHistoryList(
    historyList: ImmutableList<String>,
    onItemClick: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(historyList) { item ->
                KeyWordHistoryListContent(
                    item = item,
                    onItemClick = onItemClick
                )
            }
        }
        if (historyList.isNotEmpty()) {
            // 履歴クリアボタン
            CustomOutlinedButton(
                onClick = onClearClick,
                text = R.string.input_keyword_key_word_clear,
                textColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewKeyWordHistoryList() {
    PreviewComponentWrapper {
        KeyWordHistoryList(
            historyList = sampleHistoryList,
            onItemClick = {},
            onClearClick = {}
        )
    }
}
