package com.example.core.model.data

import kotlinx.serialization.Serializable

/**
 * 現在地の緯度経度を保持するデータクラス
 * @param latitude 緯度
 * @param longitude 経度
 */
@Serializable
data class CurrentLocation(
    val latitude: Double,
    val longitude: Double,
) {
    private companion object {
        @Suppress("ConstPropertyName", "ObjectPropertyNaming")
        private const val serialVersionUID = 1L
    }
}
