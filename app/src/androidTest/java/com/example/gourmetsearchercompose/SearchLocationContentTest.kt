package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.feature_location.R
import com.example.feature_location.component.SearchLocationContent
import com.example.feature_location.mock.FakePermissionState
import com.example.feature_location.state.LocationSearchState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalPermissionsApi::class)
class SearchLocationContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val testHelper = UITestHelper(composeTestRule)

    @Test
    fun testSearchLocationContentErrorPermissionGranted() {
        composeTestRule.setContent {
            SearchLocationContent(
                searchState = LocationSearchState.ERROR,
                locationPermissionsState = FakePermissionState(
                    PermissionStatus.Granted
                ),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        val errorMessage = context.getString(R.string.error_message)
        val retryLabel = context.getString(com.example.shared_ui.R.string.common_retry)
        val settingLabel = context.getString(R.string.setting)

        testHelper.assertTextsDisplayed(
            errorMessage,
            retryLabel,
            settingLabel
        )
    }

    @Test
    fun testSearchLocationContentErrorPermissionDenied() {
        composeTestRule.setContent {
            SearchLocationContent(
                searchState = LocationSearchState.ERROR,
                locationPermissionsState = FakePermissionState(
                    PermissionStatus.Denied(
                        shouldShowRationale = false
                    )
                ),
                onRetry = {},
                onOpenSettings = {}
            )
        }


        val errorMessage = context.getString(R.string.permission_denied_message)
        val retryLabel = context.getString(com.example.shared_ui.R.string.common_retry)
        val settingLabel = context.getString(R.string.setting)

        testHelper.assertTextsDisplayed(
            errorMessage,
            retryLabel,
            settingLabel
        )
    }

    @Test
    fun testSearchLocationContentRationalRequired() {
        composeTestRule.setContent {
            SearchLocationContent(
                searchState = LocationSearchState.RATIONAL_REQUIRED,
                locationPermissionsState = FakePermissionState(
                    PermissionStatus.Denied(
                        shouldShowRationale = true
                    )
                ),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        val rationalMessage = context.getString(R.string.permission_required_message)
        val okLabel = context.getString(com.example.shared_ui.R.string.common_ok)

        testHelper.assertTextsDisplayed(
            rationalMessage,
            okLabel
        )
    }
}