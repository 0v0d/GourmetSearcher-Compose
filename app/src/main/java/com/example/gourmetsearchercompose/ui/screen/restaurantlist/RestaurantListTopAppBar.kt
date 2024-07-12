package com.example.gourmetsearchercompose.ui.screen.restaurantlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gourmetsearchercompose.R
import com.example.gourmetsearchercompose.ui.screen.common.AppBarContent

/**
 * トップバー
 * @param onClick 戻るボタンクリック時のコールバック
 */
@Composable
fun RestaurantListTopAppBar(
    onClick: () -> Unit,
) {
    AppBarContent(
        onClick = onClick,
        title = stringResource(id = R.string.restaurant_list_search_result_top_bar_title),
    )
}
