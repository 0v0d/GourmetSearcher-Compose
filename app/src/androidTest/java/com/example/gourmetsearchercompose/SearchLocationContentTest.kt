package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.gourmetsearchercompose.mock.FakePermissionState
import com.example.gourmetsearchercompose.state.LocationSearchState
import com.example.gourmetsearchercompose.ui.screen.seachlocation.component.SearchLocationContent
import com.example.gourmetsearchercompose.utils.UITestHelper
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
    fun testSearchLocationContent_Error_PermissionGranted() {
        composeTestRule.setContent {
            SearchLocationContent(
                searchState = LocationSearchState.ERROR,
                locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        val errorMessage = context.getString(R.string.search_location_error_message)
        val retryLabel = context.getString(R.string.common_retry)
        val settingLabel = context.getString(R.string.search_location_setting)

        testHelper.assertTextsDisplayed(
            errorMessage,
            retryLabel,
            settingLabel
        )
    }

    @Test
    fun testSearchLocationContent_Error_PermissionDenied() {

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


        val errorMessage = context.getString(R.string.search_location_permission_denied_message)
        val retryLabel = context.getString(R.string.common_retry)
        val settingLabel = context.getString(R.string.search_location_setting)

        testHelper.assertTextsDisplayed(
            errorMessage,
            retryLabel,
            settingLabel
        )
    }

    @Test
    fun testSearchLocationContent_RationalRequired() {
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

        val rationalMessage =
            context.getString(R.string.search_location_permission_required_message)
        val okLabel = context.getString(R.string.common_ok)

        testHelper.assertTextsDisplayed(
            rationalMessage,
            okLabel
        )
    }
}