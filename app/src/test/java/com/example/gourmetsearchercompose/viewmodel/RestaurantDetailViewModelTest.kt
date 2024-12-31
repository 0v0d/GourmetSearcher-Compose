package com.example.gourmetsearchercompose.viewmodel

import com.example.gourmetsearchercompose.mock.MockRestaurantData.sampleRestaurantList
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import java.net.URLEncoder

/** RestaurantDetailViewModelのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailViewModelTest {
    @InjectMocks
    private lateinit var viewModel: RestaurantDetailViewModel

    /** 地図を開く機能のテスト */
    @Test
    fun testOpenMap() {
        val address = sampleRestaurantList.first().address
        val encodedAddress = URLEncoder.encode(address, "UTF-8")

        viewModel.openMap(address)

        assertEquals(encodedAddress, viewModel.address.value)
    }

    /** 住所をクリアする機能のテスト */
    @Test
    fun testClearAddress() {
        viewModel.clearAddress()

        assertEquals(null, viewModel.address.value)
    }

    /** URLを開く機能のテスト */
    @Test
    fun testOpenUrl() {
        val url = sampleRestaurantList.first().url.pc

        viewModel.openUrl(url)

        assertEquals(url, viewModel.url.value)
    }

    /** URLをクリアする機能のテスト */
    @Test
    fun testClearUrl() {
        viewModel.clearUrl()

        assertEquals(null, viewModel.url.value)
    }
}
