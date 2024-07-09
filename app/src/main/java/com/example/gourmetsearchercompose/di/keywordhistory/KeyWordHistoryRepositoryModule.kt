package com.example.gourmetsearchercompose.di.keywordhistory

import com.example.gourmetsearchercompose.manager.PreferencesManager
import com.example.gourmetsearchercompose.repository.KeyWordHistoryRepository
import com.example.gourmetsearchercompose.repository.KeyWordHistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** 検索履歴のモジュール */
@Module
@InstallIn(SingletonComponent::class)
object KeyWordHistoryRepositoryModule {
    /**
     * 検索履歴のリポジトリを提供する
     * @param preferencesManager プリファレンスマネージャ
     * @return 検索履歴のリポジトリ
     */
    @Provides
    fun provideKeyWordHistory(preferencesManager: PreferencesManager): KeyWordHistoryRepository =
        KeyWordHistoryRepositoryImpl(preferencesManager)
}
