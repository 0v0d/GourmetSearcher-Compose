package com.example.feature_keyword

import com.example.core.database.dao.KeywordDao
import com.example.core.database.model.Keyword
import com.example.feature_keyword.repository.KeyWordHistoryRepositoryImpl
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/** KeyWordHistoryRepositoryImplのユニットテストクラス */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class KeyWordHistoryRepositoryImplTest {
    private lateinit var repository: KeyWordHistoryRepositoryImpl

    @Mock
    private lateinit var keywordDao: KeywordDao

    @Before
    fun setup() {
        repository = KeyWordHistoryRepositoryImpl(keywordDao)
    }

    @Test
    fun testSaveHistoryItem() = runTest {
        val input = "test"
        val keyword = Keyword(word = input)
        `when`(keywordDao.findKeyword(input)).thenReturn(emptyList())

        repository.saveHistoryItem(input)

        verify(keywordDao).insertKeyword(keyword)
    }

    @Test
    fun testBlankInput() = runTest {
        val input = "  "
        val keyword = Keyword(word = input)

        repository.saveHistoryItem(input)

        verify(keywordDao, never()).insertKeyword(keyword)
    }

    @Test
    fun testEmptyInput() = runTest {
        val input = ""
        val keyword = Keyword(word = input)

        repository.saveHistoryItem(input)

        verify(keywordDao, never()).insertKeyword(keyword)
    }

    @Test
    fun testGetHistoryList() = runTest {
        val keywords = listOf(
            Keyword(0, "焼肉"),
            Keyword(1, "寿司"),
            Keyword(2, "ラーメン"),
        ).toImmutableList()

        `when`(keywordDao.getKeywords()).thenReturn(flowOf(keywords))

        val result = repository.getHistoryList()
        val expected = listOf("ラーメン", "寿司", "焼肉")

        result.collect { list ->
            assertEquals(expected, list)
        }
    }

    @Test
    fun testClearHistory() = runTest {
        repository.clearHistory()
        verify(keywordDao).deleteAllKeywords()
    }
}
