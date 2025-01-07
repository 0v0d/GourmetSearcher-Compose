package com.example.feature_restaurant.domain

import com.example.core.model.api.BudgetData
import com.example.core.model.api.GenreData
import com.example.core.model.api.LargeAreaData
import com.example.core.model.api.PCData
import com.example.core.model.api.PhotoData
import com.example.core.model.api.Shops
import com.example.core.model.api.SmallAreaData
import com.example.core.model.api.Urls
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * レストラン情報を保持するデータクラス
 * @param name レストラン名
 * @param address 住所
 * @param station 最寄り駅
 * @param largeArea 大エリア
 * @param smallArea 小エリア
 * @param genre ジャンル
 * @param budget 予算
 * @param access アクセス
 * @param url URL
 * @param photo 写真
 * @param open 営業時間（開店）
 * @param close 営業時間（閉店）
 */
@Serializable
data class ShopsDomain(
    val name: String,
    val address: String,
    val station: String,
    val largeArea: LargeAreaDomain,
    val smallArea: SmallAreaDomain,
    val genre: GenreDomain,
    val budget: BudgetDomain,
    val access: String,
    val url: UrlDomain,
    val photo: PhotoDomain,
    val open: String,
    val close: String,
)

/**
 * 大エリアデータクラス
 * @param name 大エリア
 */
@Serializable
data class LargeAreaDomain(
    val name: String,
)

/**
 * 小エリアデータクラス
 * @param name 小エリア
 */
@Serializable
data class SmallAreaDomain(
    val name: String,
)

/**
 * ジャンルデータクラス
 * @param name ジャンル
 */
@Serializable
data class GenreDomain(
    val name: String,
)

/**
 *  予算データクラス
 * @param name 予算
 */
@Serializable
data class BudgetDomain(
    val name: String,
)

/**
 *  URLデータクラス
 * @param pc URL
 */
@Serializable
data class UrlDomain(
    val pc: String,
)

/**
 * 写真クラス
 * @param pc 写真URL
 */
@Serializable
data class PhotoDomain(
    val pc: PCDomain,
)

/**
 * 写真データクラス
 * @param l 写真URL
 */
@Serializable
data class PCDomain(
    val l: String,
)

/**
 * APIレスポンスデータからドメインモデルへの変換関数
 * @return ドメインモデル
 */
fun Shops.toDomain() =
    ShopsDomain(
        name,
        address,
        station,
        largeArea.toDomain(),
        smallArea.toDomain(),
        genre.toDomain(),
        budget.toDomain(),
        access,
        url.toDomain(),
        photo.toDomain(),
        open,
        close,
    )

fun LargeAreaData.toDomain() = LargeAreaDomain(name)

fun SmallAreaData.toDomain() = SmallAreaDomain(name)

fun GenreData.toDomain() = GenreDomain(name)

fun BudgetData.toDomain() = BudgetDomain(name)

fun Urls.toDomain() = UrlDomain(pc)

fun PhotoData.toDomain() = PhotoDomain(pc.toDomain())

fun PCData.toDomain() = PCDomain(l)

/**
 * レストランデータをエンコードする
 * @param restaurantData レストランデータ
 * @return エンコードされた文字列
 */
fun encodeRestaurantData(restaurantData: ShopsDomain): String {
    val json = Json.encodeToString(restaurantData)
    return URLEncoder.encode(json, "UTF-8")
}

/**
 * エンコードされたレストランデータをデコードする
 * @param encoded エンコードされた文字列
 * @return デコードされたレストランデータ
 */
fun decodeRestaurantData(encoded: String): ShopsDomain {
    val json = URLDecoder.decode(encoded, "UTF-8")
    return Json.decodeFromString<ShopsDomain>(json)
}
