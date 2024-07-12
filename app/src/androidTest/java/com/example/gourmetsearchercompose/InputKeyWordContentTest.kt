package com.example.gourmetsearchercompose

import androidx.compose.ui.focus.FocusRequester

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.InputKeyWordContent
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.KeyWordHistoryList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.RangeList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.SearchTextField
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test

class InputKeyWordContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInputKeyWordContent_emptyInput() {
        composeTestRule.setContent {
            InputKeyWordContent(
                inputText = "",
                isInputEmpty = true,
                focusRequester = FocusRequester(),
                historyList = persistentListOf("History 1", "History 2"),
                onInputTextChange = {},
                onHistoryItemClick = {},
                onClearHistory = {},
                onRangeSelect = {}
            )
        }

        composeTestRule.onNodeWithTag("SearchBar").assertExists()
        composeTestRule.onNodeWithText("History 1").assertExists()
        composeTestRule.onNodeWithText("History 2").assertExists()
        composeTestRule.onNodeWithText("キーワードをクリア").assertExists()
        composeTestRule.onNodeWithText("ホーム").assertExists()
    }

    @Test
    fun testInputKeyWordContent_nonEmptyInput() {
        composeTestRule.setContent {
            InputKeyWordContent(
                inputText = "Test",
                isInputEmpty = false,
                focusRequester = FocusRequester(),
                historyList = persistentListOf(),
                onInputTextChange = {},
                onHistoryItemClick = {},
                onClearHistory = {},
                onRangeSelect = {}
            )
        }

        composeTestRule.onNodeWithTag("SearchBar").assertExists()
        composeTestRule.onNodeWithText("検索半径を選択して検索").assertExists()
    }

    @Test
    fun testSearchTextField() {
        val testQuery = "Test Query"
        composeTestRule.setContent {
            SearchTextField(
                focusRequester = FocusRequester(),
                query = testQuery,
                onQueryChange = {}
            )
        }

        composeTestRule.onNodeWithTag("textField").assertExists()
        composeTestRule.onNodeWithText(testQuery).assertExists()
        composeTestRule.onNodeWithText("キーワードを入力").assertExists()
    }

    @Test
    fun testKeyWordHistoryList() {
        composeTestRule.setContent {
            KeyWordHistoryList(
                historyList = persistentListOf("History 1", "History 2"),
                onItemClick = {},
                onClearClick = {}
            )
        }

        composeTestRule.onNodeWithText("History 1").assertExists()
        composeTestRule.onNodeWithText("History 2").assertExists()
        composeTestRule.onNodeWithText("キーワードをクリア").assertExists()
    }

    @Test
    fun testRangeList() {
        composeTestRule.setContent {
            RangeList(
                ranges = arrayOf("300", "500", "1000", "2000", "3000"),
                onRangeSelect = {}
            )
        }

        composeTestRule.onNodeWithText("検索半径を選択して検索").assertExists()
        composeTestRule.onNodeWithText("300m").assertExists()
        composeTestRule.onNodeWithText("500m").assertExists()
        composeTestRule.onNodeWithText("1000m").assertExists()
        composeTestRule.onNodeWithText("2000m").assertExists()
        composeTestRule.onNodeWithText("3000m").assertExists()
    }
}
