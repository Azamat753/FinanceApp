package com.lawlett.financeapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.usecase.balance.*
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import com.lawlett.financeapp.utils.Pref
import com.lawlett.financeapp.utils.SHARED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        getIncomeUseCase: GetIncomeUseCase,
    ): ChangeBalancePresenter = ChangeBalancePresenter(
        fillIconsUseCase,
        fillCostIconUseCase,
        saveCostUseCase,
        saveIncomeUseCase,
        getIncomeUseCase
    )

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

    @Provides
    fun createShared(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED, MODE_PRIVATE)

    @Provides
    fun createPref(sharedPreferences: SharedPreferences): Pref =
        Pref(sharedPreferences)
}