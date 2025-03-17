package com.example.core.api.model.data

import kotlinx.serialization.Serializable

/** 現在地の緯度経度を保持するデータクラス */
@Serializable
data class CurrentLocation(
    val latitude: Double,
    val longitude: Double,
) {
    private companion object {
        @Suppress( "ObjectPropertyNaming")
        private const val serialVersionUID = 1L
    }
}
