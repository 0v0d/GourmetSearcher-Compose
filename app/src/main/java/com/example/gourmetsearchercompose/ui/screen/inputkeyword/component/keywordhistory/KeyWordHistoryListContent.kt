package com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gourmetsearchercompose.mock.MockSearchTerms.KEYWORD
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleHistoryList
import com.example.gourmetsearchercompose.ui.screen.component.IconText
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.InputKeyWordContent
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewWrapper

/**
 * 履歴リストアイテム
 * @param item 履歴アイテム
 * @param onItemClick 履歴アイテムクリック時のコールバック
 * @param modifier Modifier
 */
@Composable
fun KeyWordHistoryListContent(
    item: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconText(
            icon = Icons.Default.History,
            iconSize = 20,
            text = item
        )
    }

    HorizontalDivider(
        thickness = 1.dp,
        color = Color.Gray,
    )
}

/** プレビュー */
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
