package com.lawlett.financeapp.di

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.usecase.FillCostIconUseCase
import com.lawlett.domain.usecase.FillIconsUseCase
import com.lawlett.domain.usecase.GetIncomeUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideChangeBalancePresenter(
        fillIconsUseCase: FillIconsUseCase,
        fillCostIconUseCase: FillCostIconUseCase
    ): ChangeBalancePresenter {
        return ChangeBalancePresenter(fillIconsUseCase, fillCostIconUseCase)
    }

    @Provides
    fun provideFillUseCase(balanceRepository: BalanceRepository): FillIconsUseCase {
        return FillIconsUseCase(balanceRepository)
    }

    @Provides
    fun provideGetBalanceUseCase(balanceRepository: BalanceRepository): GetIncomeUseCase {
        return GetIncomeUseCase(balanceRepository)
    }

    @Provides
    fun saveIncomeUseCase(balanceRepository: BalanceRepository): SaveIncomeUseCase {
        return SaveIncomeUseCase(balanceRepository)
    }

    @Provides
    fun fillCost(balanceRepository: BalanceRepository): FillCostIconUseCase {
        return FillCostIconUseCase(balanceRepository)
    }

}