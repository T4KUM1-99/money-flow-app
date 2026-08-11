package com.moneyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyflow.app.data.model.Transaction
import com.moneyflow.app.data.model.sampleTransactions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TransactionsUiState(
    val searchQuery: String = "",
    val groupedTransactions: Map<String, List<Transaction>> = emptyMap(),
)

class TransactionsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsUiState())
    val uiState: StateFlow<TransactionsUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = TransactionsUiState(
            groupedTransactions = sampleTransactions.groupBy { it.date }
        )
    }

    fun onSearchQueryChange(query: String) {
        val filtered = sampleTransactions.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)
        }
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            groupedTransactions = filtered.groupBy { it.date }
        )
    }
}
