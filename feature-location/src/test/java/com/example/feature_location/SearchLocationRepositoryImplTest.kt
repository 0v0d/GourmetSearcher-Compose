package com.example.feature_location

import android.location.Location
import com.example.core.api.model.data.CurrentLocation
import com.example.feature_location.mock.MockLocation.sampleLocation
import com.example.feature_location.repository.SearchLocationRepository
import com.example.feature_location.repository.SearchLocationRepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** SearchLocationRepositoryImplのユニットテストクラス */
@RunWith(MockitoJUnitRunner::class)
class SearchLocationRepositoryImplTest {
    @Mock
    private lateinit var mockLocationProvider: FusedLocationProviderClient

    @Mock
    private lateinit var mockTask: Task<Location>

    private lateinit var searchLocationRepository: SearchLocationRepository

    /** 各テスト前の準備 */
    @Before
    fun setUp() {
        searchLocationRepository = SearchLocationRepositoryImpl(mockLocationProvider)
    }

    /** 位置情報の取得が成功した場合のテスト */
    @Test
    fun testGetLocationSuccess() =
        runTest {
            val expectedLocation = sampleLocation
            val mockLocation = mock<Location>()
            `when`(mockLocation.latitude).thenReturn(expectedLocation.latitude)
            `when`(mockLocation.longitude).thenReturn(expectedLocation.longitude)
            `when`(
                mockLocationProvider.getCurrentLocation(
                    Priority.PRIORITY_LOW_POWER,
                    null
                )
            ).thenReturn(mockTask)

            doAnswer { invocation ->
                val listener = invocation.getArgument(0) as OnCompleteListener<Location>
                listener.onComplete(mockTask)
                null
            }.`when`(mockTask).addOnCompleteListener(any())

            `when`(mockTask.isSuccessful).thenReturn(true)
            `when`(mockTask.result).thenReturn(mockLocation)

            val result = searchLocationRepository.getLocation()

            assertNotNull(result)
            val location =
                result?.let {
                    CurrentLocation(it.latitude, it.longitude)
                }

            assertEquals(expectedLocation, location)
        }

    /** 位置情報の取得が失敗した場合のテスト */
    @Test
    fun testGetLocationNullOnUnsuccessful() =
        runBlocking {
            `when`(
                mockLocationProvider.getCurrentLocation(
                    Priority.PRIORITY_LOW_POWER,
                    null
                )
            ).thenReturn(mockTask)

            val result = searchLocationRepository.getLocation()

            assertNull(result)
        }

    /** セキュリティ例外が発生した場合のテスト */
    @Test
    fun testGetLocationNullOnSecurityException() =
        runBlocking {
            `when`(
                mockLocationProvider.getCurrentLocation(
                    Priority.PRIORITY_LOW_POWER,
                    null
                )
            ).thenThrow(SecurityException())

            val result = searchLocationRepository.getLocation()

            assertNull(result)
        }

    /** ラインタイムアウト例外が発生した場合のテスト */
    @Test
    fun testGetLocationNullOnGenericException() =
        runBlocking {
            `when`(
                mockLocationProvider.getCurrentLocation(
                    Priority.PRIORITY_LOW_POWER,
                    null
                )
            ).thenThrow(RuntimeException())

            val result = searchLocationRepository.getLocation()

            assertNull(result)
        }
}
