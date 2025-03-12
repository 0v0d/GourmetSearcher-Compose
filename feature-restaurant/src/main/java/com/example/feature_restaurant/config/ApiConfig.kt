package com.example.feature_restaurant.config

import com.example.feature_restaurant.BuildConfig

/** API設定 */
object ApiConfig {
    /** APIキー */
    val API_KEY = BuildConfig.API_KEY

    /** レスポンスフォーマット */
    const val FORMAT = "json"

    /** 結果表示件数 */
    const val COUNT = 50
}