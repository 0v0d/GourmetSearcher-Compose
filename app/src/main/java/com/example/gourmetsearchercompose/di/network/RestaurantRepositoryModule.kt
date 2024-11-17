package com.example.gourmetsearchercompose.di.network

import com.example.gourmetsearchercompose.manager.CacheManager
import com.example.gourmetsearchercompose.repository.RestaurantRepository
import com.example.gourmetsearchercompose.repository.RestaurantRepositoryImpl
import com.example.gourmetsearchercompose.service.HotPepperGourmetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** RestaurantRepositoryのモジュール */
@Module
@InstallIn(SingletonComponent::class)
object RestaurantRepositoryModule {
    /**
     * RestaurantRepositoryを提供
     * @param service HotPepperGourmetApiService
     * @param cacheManager CacheManager
     * @return RestaurantRepository
     */
    @Provides
    @Singleton
    fun provideGourmetShopRepository(
        service: HotPepperGourmetApiService,
        cacheManager: CacheManager,
    ): RestaurantRepository = RestaurantRepositoryImpl(service, cacheManager)
}
