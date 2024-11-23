package com.example.gourmetsearchercompose.ui.screen.seachlocation.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.gourmetsearchercompose.model.data.SearchTerms

/**
 * 画面遷移処理
 * @param searchTerms 検索条件
 * @param onNavigateToResultList 検索結果画面遷移コールバック
 */
@Composable
fun HandleLocationDataEffect(
    searchTerms: SearchTerms,
    onNavigateToResultList: (SearchTerms) -> Unit
) {
    val rememberedOnNavigate by rememberUpdatedState(onNavigateToResultList)

    LaunchedEffect(searchTerms) {
        rememberedOnNavigate(searchTerms)
    }
}
