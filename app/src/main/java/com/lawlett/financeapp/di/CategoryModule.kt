package com.lawlett.financeapp.di

import com.lawlett.data.CategoryRepositoryImpl
import com.lawlett.domain.repo.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface CategoryModule {
    @Binds
    fun category(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}