package com.example.feature_location.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.api.model.data.CurrentLocation
import com.example.feature_location.state.LocationSearchState
import com.example.feature_location.usecase.GetCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 現在地を取得するためのViewModel
 * @param getCurrentLocationUseCase 現在地を取得するためのUseCase
 */
@HiltViewModel
class SearchLocationViewModel
@Inject
constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) : ViewModel() {
    private val _locationData = MutableStateFlow<CurrentLocation?>(null)

    /** 現在地のデータ */
    val locationData = _locationData.asStateFlow()

    /**
     *現在地取得の状態
     * LOADING: 現在地取得中
     * ERROR: 現在地取得失敗
     */
    private val _locationSearchState =
        MutableStateFlow(LocationSearchState.LOADING)

    val locationSearchState = _locationSearchState.asStateFlow()

    /** パーミッションの許可がされた場合の処理 */
    fun handlePermissionGranted() {
        getLocation()
    }

    /** パーミッションの許可が拒否された場合の処理 */
    fun handlePermissionDenied() {
        _locationSearchState.value = LocationSearchState.ERROR
    }

    /** パーミッションの許可が必要な場合の処理 */
    fun handleRationaleRequired() {
        _locationSearchState.value = LocationSearchState.RATIONAL_REQUIRED
    }

    /** 現在地取得を再試行する */
    fun retryLocationRequest() {
        getLocation()
    }

    /** 現在地取得に失敗した場合の処理 */
    private fun handleError(error: Throwable) {
        _locationSearchState.value = when (error) {
            is SecurityException -> LocationSearchState.ERROR
            else -> LocationSearchState.ERROR
        }
    }

    /** 現在地を取得する */
    private fun getLocation() {
        _locationSearchState.value = LocationSearchState.LOADING
        viewModelScope.launch {
            try {
                performSearch()
            } catch (e: SecurityException) {
                handleError(e)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    /** 現在地を取得するための処理 */
    private suspend fun performSearch() {
        val location = getCurrentLocationUseCase()
        location?.let {
            handleLocationSuccess(it)
            return
        }
        _locationSearchState.value = LocationSearchState.ERROR
    }

    /**
     * 現在地取得に成功した場合の処理
     * @param location 現在地の緯度経度
     */
    private fun handleLocationSuccess(location: Location) {
        val locationData =
            CurrentLocation(location.latitude, location.longitude)
        _locationData.value = locationData
    }
}
