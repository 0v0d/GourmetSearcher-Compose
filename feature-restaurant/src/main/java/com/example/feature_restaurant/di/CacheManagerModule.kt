package com.example.feature_restaurant.di

import android.util.LruCache
import com.example.core.manager.CacheManager
import com.example.core.model.api.RestaurantList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

/** キャッシュマネージャのモジュール */
@Module
@InstallIn(SingletonComponent::class)
object CacheManagerModule {
    /** キャッシュサイズ */
    private const val CACHE_SIZE = 5

    /**
     * LruCacheを提供する
     * Size: 5
     * @return LruCache
     */
    @Provides
    @Singleton
    fun provideLruCache(): LruCache<String, Response<RestaurantList>?> = LruCache(
        CACHE_SIZE
    )

    /**
     * キャッシュマネージャを提供する
     * @return キャッシュマネージャ
     */
    @Provides
    @Singleton
    fun provideCacheManager(lruCache: LruCache<String, Response<RestaurantList>?>) =
        CacheManager(lruCache)
}
