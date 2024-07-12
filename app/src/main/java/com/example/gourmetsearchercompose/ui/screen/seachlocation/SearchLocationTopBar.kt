package com.example.gourmetsearchercompose.ui.screen.seachlocation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.ui.screen.common.AppBarContent

/**
 * トップバー
 * @param onClick 戻るボタンクリック時のコールバック
 */
@Composable
fun SearchLocationTopBar(onClick: () -> Unit) {
    AppBarContent(
        onClick = onClick,
        title = stringResource(id = R.string.search_location_top_bar_title),
    )
}
