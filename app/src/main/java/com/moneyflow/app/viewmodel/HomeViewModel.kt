package com.moneyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyflow.app.data.model.Transaction
import com.moneyflow.app.data.model.sampleTransactions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val balance: String = "Rp 12.500.000",
    val monthlyChange: String = "+12% this month",
    val recentTransactions: List<Transaction> = emptyList(),
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = HomeUiState(
            recentTransactions = sampleTransactions.take(3)
        )
    }
}
