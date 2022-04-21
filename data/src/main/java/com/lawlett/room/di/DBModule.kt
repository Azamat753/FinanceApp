package com.lawlett.room.di

import android.content.Context
import androidx.room.Room
import com.lawlett.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Volatile
    var INSTANCE: AppDataBase? = null

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance =
                Room.databaseBuilder(context, AppDataBase::class.java, "finance_database")
                    .fallbackToDestructiveMigration()
                    .build()
            INSTANCE = instance
            return instance
        }
    }

    @Provides
    @Singleton
    fun provideBalanceDao(appDataBase: AppDataBase) = appDataBase.balanceDao()

    @Provides
    @Singleton
    fun provideCategoryDao(appDataBase: AppDataBase) = appDataBase.categoryDao()

    @Provides
    @Singleton
    fun provideMonthDao(appDataBase: AppDataBase) = appDataBase.monthDao()

    @Provides
    @Singleton
    fun providePlanDao(appDataBase: AppDataBase) = appDataBase.planDao()

    @Provides
    @Singleton
    fun providePlanMonthDao(appDataBase: AppDataBase) = appDataBase.monthPlanDao()

}