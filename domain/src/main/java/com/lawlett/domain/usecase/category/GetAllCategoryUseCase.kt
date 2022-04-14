package com.lawlett.domain.usecase.category

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.CategoryRepository
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    fun getAllCategoryList(): List<BalanceModel> = repository.getAllCategory()
}