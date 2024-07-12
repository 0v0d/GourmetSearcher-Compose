package com.example.gourmetsearchercompose.ui.screen.restaurantdetail

import androidx.compose.runtime.Composable
import com.example.gourmetsearchercompose.ui.screen.common.AppBarContent

/**
 * レストラン詳細画面のトップバー
 * @param title タイトル
 * @param onClick 戻るボタンクリック時のコールバック
 */
@Composable
fun RestaurantDetailTopBar(
    title: String,
    onClick: () -> Unit,
) {
    AppBarContent(
        onClick = onClick,
        title = shortTitleBarName(title)
    )
}

private const val TITLE_LENGTH_LIMIT = 12

/**
 * タイトルバーの文字数制限を超えた場合に短縮する
 * @param title タイトル
 * @return 短縮後のタイトル
 */
private fun shortTitleBarName(title: String): String {
    return if (title.length > TITLE_LENGTH_LIMIT) {
        title.substring(0, TITLE_LENGTH_LIMIT) + "..."
    } else {
        title
    }
}
