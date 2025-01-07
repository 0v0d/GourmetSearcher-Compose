package com.example.feature_restaurant

import com.example.core.model.data.CurrentLocation
import com.example.core.model.data.GeoLocation
import com.example.core.model.data.SearchKey
import kotlinx.serialization.json.Json
import java.io.Serializable
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * 検索条件
 * @param keyword キーワード
 * @param location 現在地
 * @param range 範囲
 */
@kotlinx.serialization.Serializable
data class SearchTerms(
    val keyword: String,
    val location: CurrentLocation,
    val range: Int,
) : Serializable {
    companion object {
        @Suppress("ConstPropertyName", "ObjectPropertyNaming")
        private const val serialVersionUID = 1L
    }

    fun toSearchKey(): SearchKey {
        return SearchKey(
            keyword = keyword,
            location = GeoLocation(
                location.latitude,
                location.longitude
            ),
            range = range,
        )
    }
}


/**
 * 検索条件をエンコードする
 * @param inputText 検索キーワード
 * @param range 範囲
 * @param latitude 緯度
 * @param longitude 経度
 * @return エンコードされた検索条件
 */
fun encodeSearchTerms(
    inputText: String,
    range: Int,
    latitude: Double,
    longitude: Double
): String {
    val searchTerms = SearchTerms(
        keyword = inputText,
        location = CurrentLocation(
            latitude = latitude,
            longitude = longitude
        ),
        range = range
    )
    val json = Json.encodeToString(searchTerms)
    return URLEncoder.encode(json, "UTF-8")
}

/**
 * 検索条件をデコードする
 * @param encoded エンコードされた検索条件
 * @return デコードされた検索条件
 */
fun decodeSearchTerms(encoded: String): SearchTerms {
    val json = URLDecoder.decode(encoded, "UTF-8")
    return Json.decodeFromString<SearchTerms>(json)
}
