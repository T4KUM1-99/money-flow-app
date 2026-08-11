package com.moneyflow.app.data.model

import androidx.compose.ui.graphics.Color
import com.moneyflow.app.ui.theme.Error
import com.moneyflow.app.ui.theme.SecondaryFixed
import com.moneyflow.app.ui.theme.TertiaryContainer
import com.moneyflow.app.ui.theme.TertiaryFixed

enum class TransactionType { EXPENSE, INCOME }

data class Transaction(
    val id: String,
    val title: String,
    val category: String,
    val amount: String,
    val type: TransactionType,
    val date: String,
    val iconName: String,
    val iconBgColor: Color
)

// Sample data matching the Stitch design
val sampleTransactions = listOf(
    Transaction("1", "Coffee Shop", "Food & Drink", "-Rp 35.000", TransactionType.EXPENSE, "Today", "local_cafe", SecondaryFixed),
    Transaction("2", "Salary Corp", "Income", "+Rp 8.500.000", TransactionType.INCOME, "Today", "work", TertiaryFixed),
    Transaction("3", "Gas Station", "Transport", "-Rp 250.000", TransactionType.EXPENSE, "Today", "directions_car", Color(0xFFBDC2FF)),
    Transaction("4", "Cyber Burger", "Food", "-Rp 24.500", TransactionType.EXPENSE, "Today", "restaurant", SecondaryFixed),
    Transaction("5", "Metro Pass", "Transport", "-Rp 2.750", TransactionType.EXPENSE, "Today", "subway", Color(0xFFDFE0FF)),
    Transaction("6", "MegaMart", "Groceries", "-Rp 142.800", TransactionType.EXPENSE, "Yesterday", "shopping_cart", TertiaryFixed),
    Transaction("7", "Salary Deposit", "Income", "+Rp 3.200.000", TransactionType.INCOME, "Yesterday", "payments", Color(0xFF3A5900)),
    Transaction("8", "Electric Co.", "Utilities", "-Rp 68.400", TransactionType.EXPENSE, "Yesterday", "bolt", Color(0xFF162EE7)),
)
