package com.example.feature_keyword.di

import android.content.Context
import androidx.room.Room
import com.example.core.database.KeywordDatabase
import com.example.core.database.dao.KeywordDao
import com.example.feature_keyword.repository.KeyWordHistoryRepository
import com.example.feature_keyword.repository.KeyWordHistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** 　検索履歴のモジュール */
@Module
@InstallIn(SingletonComponent::class)
object KeyWordHistoryModule {
    /**
     * データベースを提供する
     * @param context コンテキスト
     * @return データベース
     */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        KeywordDatabase::class.java,
        "keyword_database"
    ).fallbackToDestructiveMigration()
        .build()

    /**
     * キーワードのDaoを提供する
     * @param database データベース
     * @return キーワードのDao
     */
    @Singleton
    @Provides
    fun provideKeywordDao(database:KeywordDatabase) = database.keywordDao()

    /**
     * 検索履歴のリポジトリを提供する
     * @param keywordDao キーワードのDao
     * @return 検索履歴のリポジトリ
     */
    @Provides
    @Singleton
    fun provideKeyWordHistory(keywordDao: KeywordDao): KeyWordHistoryRepository =
        KeyWordHistoryRepositoryImpl(keywordDao)
}
