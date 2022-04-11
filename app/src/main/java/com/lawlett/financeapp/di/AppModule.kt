package com.lawlett.financeapp.di

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.usecase.*
import com.lawlett.financeapp.presenter.BalancePresenter
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
        fillCostIconUseCase: FillCostIconUseCase,
        saveCostUseCase: SaveCostUseCase,
        saveIncomeUseCase: SaveIncomeUseCase,
    ): ChangeBalancePresenter {
        return ChangeBalancePresenter(
            fillIconsUseCase,
            fillCostIconUseCase,
            saveCostUseCase,
            saveIncomeUseCase
        )
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

    @Provides
    fun saveCostUseCase(balanceRepository: BalanceRepository): SaveCostUseCase {
        return SaveCostUseCase(balanceRepository)
    }
}