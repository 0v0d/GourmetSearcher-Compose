package com.example.feature_keyword

import com.example.feature_keyword.mock.MockKeyword.KEYWORD
import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList
import com.example.feature_keyword.usecase.ClearKeyWordHistoryUseCase
import com.example.feature_keyword.usecase.GetKeyWordHistoryUseCase
import com.example.feature_keyword.usecase.SaveKeyWordHistoryUseCase
import com.example.feature_keyword.viewmodel.InputKeyWordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** InputKeyWordViewModelのユニットテストクラス */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class InputKeywordViewModelTest {
    @Mock
    private lateinit var getHistoryListUseCase: GetKeyWordHistoryUseCase

    @Mock
    private lateinit var saveHistoryItemUseCase: SaveKeyWordHistoryUseCase

    @Mock
    private lateinit var clearHistoryUseCase: ClearKeyWordHistoryUseCase

    private lateinit var viewModel: InputKeyWordViewModel

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    /** 各テスト後のCleanup */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /** テキストの更新が正しく行われることを確認するテスト */
    @Test
    fun testUpdateWithNewValue() = runTest {
        val newText = KEYWORD

        viewModel =
            InputKeyWordViewModel(
                getHistoryListUseCase,
                saveHistoryItemUseCase,
                clearHistoryUseCase,
            )

        viewModel.updateInputText(newText)
        assertEquals(newText, viewModel.inputText.value)
    }

    /** 空文字列での更新が正しく行われることを確認するテスト */
    @Test
    fun testUpdateWithEmptyString() = runTest {
        val emptyText = ""

        viewModel =
            InputKeyWordViewModel(
                getHistoryListUseCase,
                saveHistoryItemUseCase,
                clearHistoryUseCase,
            )

        viewModel.updateInputText(emptyText)
        assertEquals(emptyText, viewModel.inputText.value)
    }

    /** 複数回の更新が正しく行われることを確認するテスト */
    @Test
    fun testMultipleUpdates() = runTest {
        val firstText = sampleHistoryList.first()
        val secondText = sampleHistoryList.last()

        viewModel =
            InputKeyWordViewModel(
                getHistoryListUseCase,
                saveHistoryItemUseCase,
                clearHistoryUseCase,
            )

        viewModel.updateInputText(firstText)
        assertEquals(firstText, viewModel.inputText.value)

        viewModel.updateInputText(secondText)
        assertEquals(secondText, viewModel.inputText.value)
    }

    /** 初期値が正しく設定されていることを確認するテスト */
    @Test
    fun testInitialValue() {
        viewModel =
            InputKeyWordViewModel(
                getHistoryListUseCase,
                saveHistoryItemUseCase,
                clearHistoryUseCase,
            )
        assertEquals("", viewModel.inputText.value)
    }

    /** 初期化時に履歴リストが正しく読み込まれることを確認するテスト */
    @Test
    fun testLoadHistoryOnInit() =
        runTest {
            val testHistory = sampleHistoryList
            `when`(getHistoryListUseCase()).thenReturn(flowOf(testHistory))

            viewModel =
                InputKeyWordViewModel(
                    getHistoryListUseCase,
                    saveHistoryItemUseCase,
                    clearHistoryUseCase,
                )

            assertEquals(testHistory.reversed(), viewModel.historyListData.value)
        }

    /** 新しい項目を保存した後、履歴リストが更新されることを確認するテスト */
    @Test
    fun testSaveHistoryItemUpdatesHistoryList() =
        runTest {
            val updatedHistory = sampleHistoryList

            `when`(getHistoryListUseCase()).thenReturn(
                flowOf(updatedHistory),
            )
            `when`(saveHistoryItemUseCase(anyString())).thenReturn(Unit)

            viewModel =
                InputKeyWordViewModel(
                    getHistoryListUseCase,
                    saveHistoryItemUseCase,
                    clearHistoryUseCase,
                )

            val newItem = "newItem"
            viewModel.saveHistoryItem(newItem)

            verify(saveHistoryItemUseCase).invoke(newItem)
            assertEquals(updatedHistory.reversed(), viewModel.historyListData.value)
        }

    /** 履歴リストの保存と取得を確認するテスト */
    @Test
    fun testSaveAndGetHistoryItem() =
        runTest {
            val testHistory = sampleHistoryList
            `when`(getHistoryListUseCase()).thenReturn(flowOf(testHistory))
            `when`(saveHistoryItemUseCase(anyString())).thenReturn(Unit)

            viewModel =
                InputKeyWordViewModel(
                    getHistoryListUseCase,
                    saveHistoryItemUseCase,
                    clearHistoryUseCase,
                )

            val newItem = "newItem"
            viewModel.saveHistoryItem(newItem)

            verify(saveHistoryItemUseCase).invoke(newItem)
            assertEquals(testHistory.reversed(), viewModel.historyListData.value)
        }

    /** 履歴リストのクリアを確認するテスト */
    @Test
    fun testClearHistory() =
        runTest {
            `when`(clearHistoryUseCase()).thenReturn(Unit)
            `when`(getHistoryListUseCase.invoke()).thenReturn(flowOf(emptyList()))

            viewModel =
                InputKeyWordViewModel(
                    getHistoryListUseCase,
                    saveHistoryItemUseCase,
                    clearHistoryUseCase,
                )

            viewModel.clearHistory()

            verify(clearHistoryUseCase).invoke()
            assertEquals(emptyList<String>(), viewModel.historyListData.value)
        }
}
