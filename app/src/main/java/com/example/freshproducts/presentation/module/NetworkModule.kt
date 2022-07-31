package com.example.freshproducts.presentation.module

import android.content.Context
import androidx.room.Room
import com.example.freshproducts.BuildConfig
import com.example.freshproducts.data.api.ApiService
import com.example.freshproducts.data.repo.ApiFreshOffRepo
import com.example.freshproducts.data.repo.ApiFreshRepo
import com.example.freshproducts.data.repo.ApiHelper
import com.example.freshproducts.data.repo.ApiOffHelper
import com.example.freshproducts.db.dao.FreshDao
import com.example.freshproducts.db.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()



    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiFreshRepo): ApiHelper = apiHelper

}