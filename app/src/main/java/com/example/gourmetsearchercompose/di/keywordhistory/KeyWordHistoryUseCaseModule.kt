package com.example.gourmetsearchercompose.di.keywordhistory

import com.example.gourmetsearchercompose.repository.KeyWordHistoryRepository
import com.example.gourmetsearchercompose.usecase.keywordhistory.ClearKeyWordHistoryUseCase
import com.example.gourmetsearchercompose.usecase.keywordhistory.GetKeyWordHistoryUseCase
import com.example.gourmetsearchercompose.usecase.keywordhistory.SaveKeyWordHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/** 検索履歴のユースケースのモジュール */
@Module
@InstallIn(ViewModelComponent::class)
object KeyWordHistoryUseCaseModule {
    /**
     * 検索履歴のユースケースを提供する
     * @param repository 検索履歴のリポジトリ
     * @return 検索履歴を得るためのユースケース
     */
    @Provides
    fun provideGetKeyWordHistoryUseCase(repository: KeyWordHistoryRepository): GetKeyWordHistoryUseCase =
        GetKeyWordHistoryUseCase(repository)

    /**
     * 検索履歴のユースケースを提供する
     * @param repository 検索履歴のリポジトリ
     * @return 検索履歴保存のためのユースケース
     */
    @Provides
    fun provideSaveKeyWordHistoryUseCase(repository: KeyWordHistoryRepository) = SaveKeyWordHistoryUseCase(repository)

    /**
     * 検索履歴のユースケースを提供する
     * @param repository 検索履歴のリポジトリ
     * @return 検索履歴クリアするのためのユースケース
     */
    @Provides
    fun provideClearKeyWordHistoryUseCase(repository: KeyWordHistoryRepository) = ClearKeyWordHistoryUseCase(repository)
}
