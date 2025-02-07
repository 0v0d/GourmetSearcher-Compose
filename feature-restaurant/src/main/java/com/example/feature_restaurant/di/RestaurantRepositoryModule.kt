package com.example.feature_restaurant.di

import com.example.core.api.service.HotPepperGourmetApiService
import com.example.core.cache.manager.CacheManager
import com.example.feature_restaurant.repository.RestaurantRepository
import com.example.feature_restaurant.repository.RestaurantRepositoryImpl
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
    ): RestaurantRepository =
        RestaurantRepositoryImpl(service, cacheManager)
}
