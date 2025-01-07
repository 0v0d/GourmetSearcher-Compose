package com.example.feature_location.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.core.model.data.CurrentLocation

/**
 * 画面遷移処理
 * @param keyword 検索キーワード
 * @param location 現在地情報
 * @param range 検索範囲
 * @param onNavigateToResultList 検索結果画面遷移コールバック
 */
@Composable
fun HandleLocationDataEffect(
    keyword: String,
    location: CurrentLocation,
    range: Int,
    onNavigateToResultList: (
        inputText: String,
        range: Int,
        latitude: Double,
        longitude: Double
    ) -> Unit
) {
    val rememberedOnNavigate by rememberUpdatedState(onNavigateToResultList)

    LaunchedEffect(keyword, range, location) {
        rememberedOnNavigate(keyword, range, location.latitude, location.longitude)
    }
}

