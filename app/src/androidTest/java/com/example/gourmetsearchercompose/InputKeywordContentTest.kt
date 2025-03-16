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

    /** キーワード履歴がある場合、履歴リストとクリアボタンが表示されることを確認 */
    @Test
    fun testShowHistoryListAndClearButton() {
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

    /** 入力テキストと範囲選択リストが表示されることを確認 */
    @Test
    fun testShowInputTextAndRangeList() {
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

    /** テキストと検索テキストフィールドが表示されることを確認 */
    @Test
    fun testShowTextAndSearchTextField() {
        val testQuery = "Test Query"
        composeTestRule.setContent {
            SearchTextField(
                focusRequester = FocusRequester(),
                query = testQuery,
                onQueryChange = {}
            )
        }

        composeTestRule.onNodeWithTag("searchTextField").assertExists()
        val hint = context.getString(R.string.search_input_hint)
        testHelper.assertTextsDisplayed(
            testQuery,
            hint
        )
    }

    /** キーワード履歴リストが表示されることを確認 */
    @Test
    fun testShowKeyWordHistoryList() {
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

        val clearLabel = context.getString(R.string.key_word_clear)
        testHelper.assertTextsDisplayed(clearLabel)
    }

    /** 範囲選択リストが表示されることを確認 */
    @Test
    fun testRangeList() {
        composeTestRule.setContent {
            RangeList(
                onRangeSelect = {}
            )
        }

        val rangeLabel = context.getString(R.string.select_range_explanation)
        testHelper.assertTextsDisplayed(rangeLabel)

        val rangeArray = context.resources.getStringArray(R.array.range_array)
        val meter = context.getString(R.string.meter)
        for (range in rangeArray) {
            testHelper.assertTextsDisplayed(range + meter)
        }
    }
}
