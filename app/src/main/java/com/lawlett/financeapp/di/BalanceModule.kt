package com.lawlett.financeapp.di

import com.lawlett.data.BalanceRepositoryImpl
import com.lawlett.domain.BalanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface BalanceModule {

    @Binds
    fun balance(balanceImpl: BalanceRepositoryImpl): BalanceRepository
}