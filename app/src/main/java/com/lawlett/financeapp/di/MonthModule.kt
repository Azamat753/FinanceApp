package com.lawlett.financeapp.di

import com.lawlett.data.MonthRepositoryImpl
import com.lawlett.domain.repo.MonthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface MonthModule {
    @Binds
    fun category(monthRepositoryImpl: MonthRepositoryImpl): MonthRepository
}