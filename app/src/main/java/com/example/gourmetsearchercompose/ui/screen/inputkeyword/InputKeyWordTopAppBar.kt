package com.example.gourmetsearchercompose.ui.screen.inputkeyword

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gourmetsearchercompose.R

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
