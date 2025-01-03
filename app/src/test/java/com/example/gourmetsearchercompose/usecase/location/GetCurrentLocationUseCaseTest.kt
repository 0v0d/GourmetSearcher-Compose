package com.example.gourmetsearchercompose.usecase.location

import android.location.Location
import com.example.gourmetsearchercompose.mock.MockSearchTerms.sampleSearchTerms
import com.example.gourmetsearchercompose.model.data.CurrentLocation
import com.example.gourmetsearchercompose.repository.SearchLocationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** GetCurrentLocationUseCaseのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class GetCurrentLocationUseCaseTest {
    @Mock
    private lateinit var searchLocationRepository: SearchLocationRepository

    @InjectMocks
    private lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    /** 正しく位置情報が取得できることを確認するテスト */
    @Test
    fun testInvokeReturnsLocationSuccessful() =
        runBlocking {
            val expectedLocation = sampleSearchTerms.location
            val mockLocation = mock<Location>()
            `when`(mockLocation.latitude).thenReturn(expectedLocation.latitude)
            `when`(mockLocation.longitude).thenReturn(expectedLocation.longitude)
            `when`(searchLocationRepository.getLocation()).thenReturn(mockLocation)

            val result =
                getCurrentLocationUseCase()?.let {
                    CurrentLocation(it.latitude, it.longitude)
                }

            assertEquals(expectedLocation, result)
        }

    /** 位置情報の取得に失敗した場合にnullが返却されることを確認するテスト */
    @Test
    fun testInvokeReturnsNull() =
        runBlocking {
            `when`(searchLocationRepository.getLocation()).thenReturn(null)

            val result = getCurrentLocationUseCase()

            assertNull(result)
        }
}
