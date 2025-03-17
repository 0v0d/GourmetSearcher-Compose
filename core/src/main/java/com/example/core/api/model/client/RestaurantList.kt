package com.example.core.api.model.client

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

/** ホットペッパーグルメAPIのレスポンスモデル */
@Serializable
data class RestaurantList(
    @Json(name = "results")
    val results: Results,
)

@Serializable
data class Results(
    @Json(name = "shop")
    val shops: List<Shops>,
)

/**
 * レストラン情報を表すデータクラス
 * APIレスポンスの各店舗情報とマッピング
 */
@Serializable
data class Shops(
    @Json(name = "name")
    val name: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "station_name")
    val station: String,
    @Json(name = "large_area")
    val largeArea: LargeAreaData,
    @Json(name = "small_area")
    val smallArea: SmallAreaData,
    @Json(name = "genre")
    val genre: GenreData,
    @Json(name = "budget")
    val budget: BudgetData,
    @Json(name = "mobile_access")
    val access: String,
    @Json(name = "urls")
    val url: Urls,
    @Json(name = "photo")
    val photo: PhotoData,
    @Json(name = "open")
    val open: String,
    @Json(name = "close")
    val close: String,
)

@Serializable
data class LargeAreaData(
    @Json(name = "name")
    val name: String,
)

@Serializable
data class SmallAreaData(
    @Json(name = "name")
    val name: String,
)

@Serializable
data class GenreData(
    @Json(name = "name")
    val name: String,
)

@Serializable
data class BudgetData(
    @Json(name = "name")
    val name: String,
)

@Serializable
data class Urls(
    @Json(name = "pc")
    val pc: String,
)

@Serializable
data class PhotoData(
    @Json(name = "pc")
    val pc: PCData,
)

@Serializable
data class PCData(
    @Json(name = "l")
    val l: String,
)
