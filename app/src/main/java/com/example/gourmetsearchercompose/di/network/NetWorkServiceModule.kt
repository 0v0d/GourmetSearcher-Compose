package com.example.gourmetsearchercompose.di.network

import com.example.gourmetsearchercompose.service.HotPepperGourmetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/** HotPepperGourmetApiServiceのモジュール */
@Module
@InstallIn(SingletonComponent::class)
object NetWorkServiceModule {
    /**
     * HotPepperGourmetApiServiceを提供
     * @param retrofit Retrofit
     * @return HotPepperGourmetApiService
     */
    @Provides
    fun provideHotPepperService(
        retrofit: Retrofit
    ): HotPepperGourmetApiService = retrofit.create(HotPepperGourmetApiService::class.java)
}
