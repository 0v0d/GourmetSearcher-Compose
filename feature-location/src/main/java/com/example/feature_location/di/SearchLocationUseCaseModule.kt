package com.example.feature_location.di

import com.example.feature_location.repository.SearchLocationRepository
import com.example.feature_location.usecase.GetCurrentLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/** 位置情報のユースケースのモジュール */
@Module
@InstallIn(ViewModelComponent::class)
object SearchLocationUseCaseModule {
    /**
     * 位置情報のユースケースを提供する
     * @param searchLocationRepository 位置情報のリポジトリ
     * @return 位置情報のユースケース
     */
    @Provides
    fun provideLocationUseCase(searchLocationRepository: SearchLocationRepository): GetCurrentLocationUseCase =
        GetCurrentLocationUseCase(
            searchLocationRepository
        )
}
