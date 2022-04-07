package com.lawlett.financeapp.di

import com.lawlett.room.di.DBModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        AppModule::class,
        DBModule::class,
        BalanceModule::class
    ]
)
interface AppAggregatorModule