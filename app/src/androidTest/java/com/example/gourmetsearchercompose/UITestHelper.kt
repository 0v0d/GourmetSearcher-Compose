package com.example.gourmetsearchercompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

class UITestHelper(private val composeTestRule: ComposeContentTestRule) {
    fun assertTextsDisplayed(vararg texts: String) {
        texts.forEach { text ->
            composeTestRule.onNodeWithText(text).assertIsDisplayed()
        }
    }
}