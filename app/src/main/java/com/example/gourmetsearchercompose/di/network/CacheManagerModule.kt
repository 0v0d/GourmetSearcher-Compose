package com.example.gourmetsearchercompose.di.network

import android.util.LruCache
import com.example.gourmetsearchercompose.manager.CacheManager
import com.example.gourmetsearchercompose.model.api.RestaurantList
import com.example.gourmetsearchercompose.model.data.SearchTerms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response

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
    fun provideLruCache(): LruCache<SearchTerms, Response<RestaurantList>?> = LruCache(CACHE_SIZE)

    /**
     * キャッシュマネージャを提供する
     * @return キャッシュマネージャ
     */
    @Provides
    fun provideCacheManager(lruCache: LruCache<SearchTerms, Response<RestaurantList>?>) = CacheManager(lruCache)
}
