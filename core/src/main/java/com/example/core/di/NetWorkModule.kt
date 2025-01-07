package com.example.core.di

import com.example.core.service.HotPepperGourmetApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/** NetWorkのモジュール */
@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    private const val BASE_URL = "https://webservice.recruit.co.jp/hotpepper/"
    /**
     * Moshiを提供
     * @return Moshi
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    /**
     * Retrofitを提供
     * @param moshi Moshi
     * @return Retrofit
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    /**
     * HotPepperGourmetApiServiceを提供
     * @param retrofit Retrofit
     * @return HotPepperGourmetApiService
     */
    @Provides
    @Singleton
    fun provideHotPepperService(
        retrofit: Retrofit
    ): HotPepperGourmetApiService = retrofit.create(HotPepperGourmetApiService::class.java)
}
