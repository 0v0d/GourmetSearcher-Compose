package com.example.feature_keyword.di

import com.example.feature_keyword.repository.KeyWordHistoryRepository
import com.example.feature_keyword.usecase.ClearKeyWordHistoryUseCase
import com.example.feature_keyword.usecase.GetKeyWordHistoryUseCase
import com.example.feature_keyword.usecase.SaveKeyWordHistoryUseCase
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
    fun provideGetKeyWordHistoryUseCase(repository: KeyWordHistoryRepository) =
        GetKeyWordHistoryUseCase(repository)

    /**
     * 検索履歴のユースケースを提供する
     * @param repository 検索履歴のリポジトリ
     * @return 検索履歴保存のためのユースケース
     */
    @Provides
    fun provideSaveKeyWordHistoryUseCase(repository: KeyWordHistoryRepository) =
        SaveKeyWordHistoryUseCase(repository)

    /**
     * 検索履歴のユースケースを提供する
     * @param repository 検索履歴のリポジトリ
     * @return 検索履歴クリアするのためのユースケース
     */
    @Provides
    fun provideClearKeyWordHistoryUseCase(repository: KeyWordHistoryRepository) =
        ClearKeyWordHistoryUseCase(repository)
}
