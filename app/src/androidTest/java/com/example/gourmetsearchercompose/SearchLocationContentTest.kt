package com.example.gourmetsearchercompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.gourmetsearchercompose.state.LocationSearchState
import com.example.gourmetsearchercompose.ui.screen.preview.FakePermissionState
import com.example.gourmetsearchercompose.ui.screen.seachlocation.SearchLocationContent
import com.example.gourmetsearchercompose.ui.screen.seachlocation.SearchLocationTopBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalPermissionsApi::class)
class SearchLocationContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchLocationContent_Error_PermissionGranted() {
        composeTestRule.setContent {
            SearchLocationContent(
                onClick = {},
                searchState = LocationSearchState.Error,
                locationPermissionsState = FakePermissionState(PermissionStatus.Granted),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        composeTestRule.onNodeWithText("位置情報を取得できませんでした").assertIsDisplayed()
        composeTestRule.onNodeWithText("リトライ").assertIsDisplayed()
        composeTestRule.onNodeWithText("設定を開く").assertIsDisplayed()
    }

    @Test
    fun testSearchLocationContent_Error_PermissionDenied() {

        composeTestRule.setContent {
            SearchLocationContent(
                onClick = {},
                searchState = LocationSearchState.Error,
                locationPermissionsState =FakePermissionState(
                    PermissionStatus.Denied(
                        shouldShowRationale = false
                    )
                ),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        composeTestRule.onNodeWithText("位置情報が許可されていません").assertIsDisplayed()
        composeTestRule.onNodeWithText("リトライ").assertIsDisplayed()
        composeTestRule.onNodeWithText("設定を開く").assertIsDisplayed()
    }

    @Test
    fun testSearchLocationContent_RationalRequired() {
        composeTestRule.setContent {
            SearchLocationContent(
                onClick = {},
                searchState = LocationSearchState.RationalRequired,
                locationPermissionsState = FakePermissionState(
                    PermissionStatus.Denied(
                        shouldShowRationale = true
                    )
                ),
                onRetry = {},
                onOpenSettings = {}
            )
        }

        composeTestRule.onNodeWithText("このアプリでは、位置情報を取得するために位置情報パーミッションが必要です").assertIsDisplayed()
        composeTestRule.onNodeWithText("OK").assertIsDisplayed()
    }

    @Test
    fun testSearchLocationTopBar() {
        composeTestRule.setContent {
            SearchLocationTopBar(onClick = {})
        }

        composeTestRule.onNodeWithText("現在位置取得").assertIsDisplayed()
    }
}