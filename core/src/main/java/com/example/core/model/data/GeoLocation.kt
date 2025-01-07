package com.example.core.model.data

import java.math.RoundingMode

/**
 * 地理情報を表すクラス
 * @param latitude 緯度
 * @param longitude 経度
 */
data class GeoLocation(
    val latitude: Double,
    val longitude: Double,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GeoLocation) return false
        return latitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP) ==
               other.latitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP) &&
               longitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP) ==
               other.longitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP)
    }

    override fun hashCode(): Int {
        return latitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP).hashCode() * 31 +
               longitude.toBigDecimal().setScale(6, RoundingMode.HALF_UP).hashCode()
    }
}
