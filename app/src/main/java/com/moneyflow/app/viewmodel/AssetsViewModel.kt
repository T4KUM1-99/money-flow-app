package com.moneyflow.app.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyflow.app.data.model.AllocationSlice
import com.moneyflow.app.data.model.Asset
import com.moneyflow.app.data.model.sampleAllocation
import com.moneyflow.app.data.model.sampleAssets
import com.moneyflow.app.data.model.sparklinePoints
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AssetsUiState(
    val totalAssets: String = "Rp 85.400",
    val monthlyChange: String = "+4.2%",
    val sparkline: List<Pair<Float, Float>> = emptyList(),
    val allocation: List<AllocationSlice> = emptyList(),
    val assets: List<Asset> = emptyList(),
)

class AssetsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AssetsUiState())
    val uiState: StateFlow<AssetsUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = AssetsUiState(
            sparkline = sparklinePoints,
            allocation = sampleAllocation,
            assets = sampleAssets,
        )
    }
}
