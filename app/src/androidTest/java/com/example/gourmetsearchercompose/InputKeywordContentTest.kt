package com.example.gourmetsearchercompose

import android.content.Context
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.feature_keyword.R
import com.example.feature_keyword.component.InputKeyWordContent
import com.example.feature_keyword.component.keywordhistory.KeyWordHistoryList
import com.example.feature_keyword.component.range.RangeList
import com.example.feature_keyword.component.textfield.SearchTextField
import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.junit.Rule
import org.junit.Test

class InputKeywordContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val mockHistoryList = sampleHistoryList.toPersistentList()
    private val testHelper = UITestHelper(composeTestRule)

    @Test
    fun testInputKeyWordContentEmptyInput() {
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
        val clearLabel = context.getString(R.string.key_word_clear)
        testHelper.assertTextsDisplayed(clearLabel)
    }

    @Test
    fun testInputKeyWordContentNonEmptyInput() {
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
        val rangeLabel = context.getString(R.string.select_range_explanation)
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
        val hint = context.getString(R.string.search_input_hint)
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
        val clearLabel = context.getString(R.string.key_word_clear)
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
        val rangeLabel = context.getString(R.string.select_range_explanation)
        testHelper.assertTextsDisplayed(rangeLabel)

        val rangeArray = context.resources.getStringArray(R.array.range_array)
        val meter = context.getString(R.string.meter)
        for (range in rangeArray) {
            testHelper.assertTextsDisplayed(range + meter)
        }
    }
}
