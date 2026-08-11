package com.moneyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyflow.app.data.model.BudgetCategory
import com.moneyflow.app.data.model.sampleBudgetCategories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BudgetUiState(
    val month: String = "May 2024",
    val totalSpent: String = "Rp 3.500.000",
    val totalLimit: String = "Rp 5.000.000",
    val usedPercent: Float = 0.70f,
    val categories: List<BudgetCategory> = emptyList(),
)

class BudgetViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetUiState())
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = BudgetUiState(categories = sampleBudgetCategories)
    }
}
