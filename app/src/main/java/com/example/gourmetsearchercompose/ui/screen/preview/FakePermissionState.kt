package com.example.gourmetsearchercompose.ui.screen.preview

import android.Manifest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

/**
 * パーミッションステータスを返すダミークラス
 * @param initialStatus パーミッションステータス
 * @return パーミッションステータス
 */
@OptIn(ExperimentalPermissionsApi::class)
class FakePermissionState(initialStatus: PermissionStatus) : PermissionState {
    override val permission: String = Manifest.permission.ACCESS_COARSE_LOCATION
    override var status: PermissionStatus = initialStatus

    @Suppress("EmptyFunctionBlock")
    override fun launchPermissionRequest() {
    }
}
