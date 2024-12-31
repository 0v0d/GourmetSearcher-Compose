package com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.range

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleHistoryList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory.KeyWordHistoryList
import com.example.gourmetsearchercompose.ui.screen.preview.component.PreviewComponentWrapper

/**
 * 範囲リストコンテンツ
 * @param onRangeSelect 範囲選択時のコールバック
 * @param modifier Modifier
 */
@Composable
fun RangeListContent(
    onRangeSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val ranges = stringArrayResource(R.array.input_keyword_range_array).map {
        it + stringResource(R.string.input_keyword_meter)
    }

    LazyColumn(modifier = modifier) {
        itemsIndexed(ranges) { index, range ->
            Text(
                text = range,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRangeSelect(index) }
                    .padding(8.dp)
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray
            )
        }
    }
}

/** プレビュー */
@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewRangeList() {
    PreviewComponentWrapper {
        KeyWordHistoryList(
            historyList = sampleHistoryList,
            onItemClick = {},
            onClearClick = {}
        )
    }
}
