package com.example.feature_restaurant.di

import com.example.core.api.service.HotPepperGourmetApiService
import com.example.core.cache.manager.CacheManager
import com.example.feature_restaurant.repository.RestaurantRepository
import com.example.feature_restaurant.repository.RestaurantRepositoryImpl
import com.example.feature_restaurant.source.CachedRestaurantDataSourceProxy
import com.example.feature_restaurant.source.RemoteRestaurantDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** データソースのモジュール */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    /**
     * RemoteRestaurantDataSourceを提供
     * @param service ホットペッパーグルメAPIサービス
     * @return RemoteRestaurantDataSource
     */
    @Provides
    @Singleton
    fun provideRestaurantDataSource(
        service: HotPepperGourmetApiService
    ): RemoteRestaurantDataSource {
        return RemoteRestaurantDataSource(service)
    }

    /**
     * CachedRestaurantDataSourceProxyを提供
     * @param remoteDataSource リモートデータソース
     * @param cacheManager キャッシュ管理
     * @return CachedRestaurantDataSourceProxy
     */
    @Provides
    @Singleton
    fun provideCachedRestaurantDataSource(
        remoteDataSource: RemoteRestaurantDataSource,
        cacheManager: CacheManager
    ): CachedRestaurantDataSourceProxy {
        return CachedRestaurantDataSourceProxy(remoteDataSource, cacheManager)
    }


    /**
     * RestaurantRepositoryを提供
     * @param dataSource データソース
     * @return RestaurantRepository
     */
    @Provides
    @Singleton
    fun provideRestaurantRepository(
        dataSource: CachedRestaurantDataSourceProxy
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(dataSource)
    }
}
