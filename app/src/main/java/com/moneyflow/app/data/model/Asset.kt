package com.moneyflow.app.data.model

import androidx.compose.ui.graphics.Color
import com.moneyflow.app.ui.theme.Error
import com.moneyflow.app.ui.theme.OnPrimaryFixed
import com.moneyflow.app.ui.theme.PrimaryFixed
import com.moneyflow.app.ui.theme.SecondaryContainer
import com.moneyflow.app.ui.theme.TertiaryContainer
import com.moneyflow.app.ui.theme.TertiaryFixed

data class Asset(
    val id: String,
    val name: String,
    val type: String,
    val value: String,
    val changePercent: String,
    val isPositive: Boolean,
    val iconName: String,
    val iconBgColor: Color
)

data class AllocationSlice(
    val label: String,
    val percentage: Float,
    val color: Color
)

val sampleAssets = listOf(
    Asset("1", "Bank Central", "Savings",   "Rp 29.890", "+1.2%", true,  "account_balance", PrimaryFixed),
    Asset("2", "IndoStock",    "Equities",  "Rp 34.160", "+8.4%", true,  "show_chart",       TertiaryFixed),
    Asset("3", "Digital Gold", "Commodity", "Rp 12.810", "-0.5%", false, "diamond",          SecondaryContainer),
)

val sampleAllocation = listOf(
    AllocationSlice("Stocks",  0.40f, Color(0xFF162EE7)),
    AllocationSlice("Savings", 0.35f, Color(0xFFA9F900)),
    AllocationSlice("Gold",    0.15f, Color(0xFFFDBEC9)),
    AllocationSlice("Cash",    0.10f, Color(0xFFBDC2FF)),
)

// Sparkline path points [0f..1f] normalised
val sparklinePoints = listOf(
    0f to 0.83f, 0.1f to 0.67f, 0.2f to 0.75f, 0.3f to 0.33f, 0.4f to 0.5f,
    0.5f to 0.17f, 0.6f to 0.42f, 0.7f to 0.25f, 0.8f to 0.58f,
    0.9f to 0.08f, 1f to 0.17f
)
