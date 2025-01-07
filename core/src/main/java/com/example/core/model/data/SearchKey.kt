package com.example.core.model.data

/**
 * 検索条件をキャッシュのキーとして使用するクラス
 * @param keyword キーワード
 * @param location 現在地情報
 * @param range 検索範囲
 */
data class SearchKey(
    val keyword: String,
    val location: GeoLocation,
    val range: Int,
) {
    /**
     * キャッシュキーを生成する
     * @return 文字列形式のキャッシュキー
     */
    fun generateCacheKey(): String {
        return "${keyword}:${location.latitude},${location.longitude}:${range}"
    }
}
