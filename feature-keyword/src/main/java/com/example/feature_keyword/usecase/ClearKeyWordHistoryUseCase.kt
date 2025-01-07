package com.example.feature_keyword.usecase

import com.example.feature_keyword.repository.KeyWordHistoryRepository
import javax.inject.Inject

/**
 * 検索履歴をクリアするUseCase
 * @param repository KeyWordHistoryRepository
 */
class ClearKeyWordHistoryUseCase
@Inject
constructor(
    private val repository: KeyWordHistoryRepository,
) {
    /** 検索履歴をクリアする */
    suspend operator fun invoke() = repository.clearHistory()
}
