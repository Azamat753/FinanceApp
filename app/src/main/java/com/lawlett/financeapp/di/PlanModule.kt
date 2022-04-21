package com.lawlett.financeapp.di

import com.lawlett.data.PlanRepositoryImpl
import com.lawlett.domain.repo.PlanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PlanModule {
    @Binds
    fun plan(planRepositoryImpl: PlanRepositoryImpl): PlanRepository
}