package com.example.feature_keyword

import com.example.feature_keyword.mock.MockKeyword.sampleHistoryList
import com.example.feature_keyword.repository.KeyWordHistoryRepository
import com.example.feature_keyword.usecase.GetKeyWordHistoryUseCase
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
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** GetKeyWordHistoryUseCaseのユニットテストクラス */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetKeywordHistoryUseCaseTest {
    @Mock
    private lateinit var keyWordHistoryRepository: KeyWordHistoryRepository

    @InjectMocks
    private lateinit var getKeyWordHistoryUseCase: GetKeyWordHistoryUseCase

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

    /** invokeが履歴リストを返すかテスト */
    @Test
    fun testInvokeReturnsHistoryList() =
        runTest {
            val expectedHistoryList = sampleHistoryList
            `when`(keyWordHistoryRepository.getHistoryList()).thenReturn(flowOf(expectedHistoryList))

            val result = getKeyWordHistoryUseCase()
            result.collect { assertEquals(expectedHistoryList, it) }
        }
}
