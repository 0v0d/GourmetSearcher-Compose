package com.example.feature_keyword.component.keywordhistory

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
import com.example.feature_keyword.R
import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList
import com.example.shared_ui.CustomOutlinedButton
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
                text = R.string.key_word_clear,
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
@Preview(showBackground = true)
@Composable
private fun PreviewKeyWordHistoryList() {
    KeyWordHistoryList(
        historyList = sampleHistoryList,
        onItemClick = {},
        onClearClick = {}
    )
}
