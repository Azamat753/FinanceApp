package com.lawlett.room.di

import android.content.Context
import androidx.room.Room
import com.lawlett.data.BalanceRepositoryImpl
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
                Room.databaseBuilder(context, AppDataBase::class.java, "finance_database").build()
            INSTANCE = instance
            return instance
        }
    }

    @Provides
    @Singleton
    fun provideBalanceDao(appDataBase: AppDataBase) = appDataBase.balanceDao()

}