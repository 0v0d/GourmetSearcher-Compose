package com.example.gourmetsearchercompose.usecase.keywordhistory

import com.example.gourmetsearchercompose.repository.KeyWordHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 検索履歴を取得するUseCase
 * @param repository KeyWordHistoryRepository
 */
class GetKeyWordHistoryUseCase
@Inject
constructor(
    private val repository: KeyWordHistoryRepository,
) {
    /**
     * 検索履歴を取得する
     * @return 検索履歴
     */
    operator fun invoke(): Flow<List<String>> = repository.getHistoryList()
}
