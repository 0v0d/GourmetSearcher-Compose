package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.InputKeyWordContent
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.keywordhistory.KeyWordHistoryList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.range.RangeList
import com.example.gourmetsearchercompose.ui.screen.inputkeyword.component.textfield.SearchTextField
import com.example.gourmetsearchercompose.utils.UITestHelper
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test

class InputKeyWordContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val mockHistoryList = persistentListOf("History 1", "History 2")

    private val testHelper = UITestHelper(composeTestRule)

    @Test
    fun testInputKeyWordContent_emptyInput() {
        composeTestRule.setContent {
            InputKeyWordContent(
                inputText = "",
                focusRequester = FocusRequester(),
                historyList = mockHistoryList,
                onInputTextChange = {},
                onClearHistory = {},
                onRangeSelect = {}
            )
        }

        composeTestRule.onNodeWithTag("SearchBar").assertExists()
        for (mockHistory in mockHistoryList) {
            testHelper.assertTextsDisplayed(mockHistory)
        }

        //キーワードをクリア
        val clearLabel = context.getString(R.string.input_keyword_key_word_clear)
        testHelper.assertTextsDisplayed(clearLabel)
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
        val rangeLabel = context.getString(R.string.input_keyword_select_range_explanation)
        testHelper.assertTextsDisplayed(rangeLabel)
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
        //キーワードを入力
        val hint = context.getString(R.string.input_keyword_search_input_hint)
        testHelper.assertTextsDisplayed(
            testQuery,
            hint
        )
    }

    @Test
    fun testKeyWordHistoryList() {
        composeTestRule.setContent {
            KeyWordHistoryList(
                historyList = mockHistoryList,
                onItemClick = {},
                onClearClick = {}
            )
        }

        for (mockHistory in mockHistoryList) {
            composeTestRule.onNodeWithText(mockHistory).assertExists()
        }

        //キーワードをクリア
        val clearLabel = context.getString(R.string.input_keyword_key_word_clear)
        testHelper.assertTextsDisplayed(clearLabel)
    }

    @Test
    fun testRangeList() {
        composeTestRule.setContent {
            RangeList(
                onRangeSelect = {}
            )
        }

        //検索半径を選択して検索
        val rangeLabel = context.getString(R.string.input_keyword_select_range_explanation)
        testHelper.assertTextsDisplayed(rangeLabel)

        val rangeArray = context.resources.getStringArray(R.array.input_keyword_range_array)
        val meter = context.getString(R.string.input_keyword_meter)
        for (range in rangeArray) {
            testHelper.assertTextsDisplayed(range + meter)
        }
    }
}
