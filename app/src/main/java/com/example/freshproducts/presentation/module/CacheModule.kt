package com.example.freshproducts.presentation.module

import android.content.Context
import androidx.room.Room
import com.example.freshproducts.data.repo.ApiFreshOffRepo
import com.example.freshproducts.data.repo.ApiOffHelper
import com.example.freshproducts.db.dao.FreshDao
import com.example.freshproducts.db.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {


    @Singleton
    @Provides
    fun provideFreshDAO(roomDatabase: AppDatabase): FreshDao {
        return roomDatabase.freshDao()
    }

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideApiOffHelper(apiHelper: ApiFreshOffRepo): ApiOffHelper = apiHelper
}