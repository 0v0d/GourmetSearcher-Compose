package com.example.feature_keyword

import com.example.feature_keyword.mock.MockKeyword.KEYWORD
import com.example.feature_keyword.repository.KeyWordHistoryRepository
import com.example.feature_keyword.usecase.SaveKeyWordHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/** SaveKeyWordHistoryUseCaseのユニットテストクラス */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SaveKeyWordHistoryUseCaseTest {
    @Mock
    private lateinit var keyWordHistoryRepository: KeyWordHistoryRepository

    @InjectMocks
    private lateinit var saveKeyWordHistoryUseCase: SaveKeyWordHistoryUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    /** 各テスト前の準備 */
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    /** 各テスト後のCleanup */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /** invokeが正しく呼び出されるかテスト */
    @Test
    fun testInvokeCallsSaveHistoryItem() =
        runTest {
            val keyword = KEYWORD

            saveKeyWordHistoryUseCase(keyword)

            verify(keyWordHistoryRepository).saveHistoryItem(keyword)
        }
}
