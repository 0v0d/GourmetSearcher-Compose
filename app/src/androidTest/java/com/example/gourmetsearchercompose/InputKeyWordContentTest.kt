package com.example.gourmetsearchercompose

import androidx.compose.ui.focus.FocusRequester

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.InputKeyWordContent
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory.KeyWordHistoryList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.range.RangeList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.textfield.SearchTextField
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
                focusRequester = FocusRequester(),
                historyList = persistentListOf("History 1", "History 2"),
                onInputTextChange = {},
                onClearHistory = {},
                onRangeSelect = {}
            )
        }

        composeTestRule.onNodeWithTag("SearchBar").assertExists()
        composeTestRule.onNodeWithText("History 1").assertExists()
        composeTestRule.onNodeWithText("History 2").assertExists()
        composeTestRule.onNodeWithText("キーワードをクリア").assertExists()
    }

    @Test
    fun testInputKeyWordContent_nonEmptyInput() {
        composeTestRule.setContent {
            InputKeyWordContent(
                inputText = "Test",
                focusRequester = FocusRequester(),
                historyList = persistentListOf(),
                onInputTextChange = {},
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

        composeTestRule.onNodeWithTag("searchTextField").assertExists()
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
