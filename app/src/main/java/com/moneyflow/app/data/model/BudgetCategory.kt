package com.moneyflow.app.data.model

import androidx.compose.ui.graphics.Color
import com.moneyflow.app.ui.theme.Error
import com.moneyflow.app.ui.theme.InversePrimary
import com.moneyflow.app.ui.theme.SecondaryContainer
import com.moneyflow.app.ui.theme.TertiaryFixed

data class BudgetCategory(
    val id: String,
    val name: String,
    val spent: String,
    val total: String,
    val percentage: Float,           // 0f–1f
    val iconName: String,
    val iconBgColor: Color,
    val progressColor: Color
)

val sampleBudgetCategories = listOf(
    BudgetCategory("1", "Food & Drink",    "Rp 1.275.000", "Rp 1.500.000", 0.85f, "restaurant",     SecondaryContainer, Error),
    BudgetCategory("2", "Transport",       "Rp 450.000",   "Rp 1.000.000", 0.45f, "directions_car", TertiaryFixed,      TertiaryFixed),
    BudgetCategory("3", "Entertainment",   "Rp 120.000",   "Rp 800.000",   0.15f, "sports_esports",  InversePrimary,     InversePrimary),
)
