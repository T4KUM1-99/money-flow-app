package com.moneyflow.app.ui.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moneyflow.app.ui.components.AppTopBar
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistTextField
import com.moneyflow.app.ui.components.TransactionRow
import com.moneyflow.app.ui.theme.*
import com.moneyflow.app.viewmodel.TransactionsViewModel

@Composable
fun TransactionsScreen(
    onScanReceipt: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Transactions") },
        containerColor = Background,
        floatingActionButton = {
            // Floating Action Button matching HTML
            Box(modifier = Modifier.padding(bottom = 80.dp)) {
                BrutalistButton(
                    onClick = onScanReceipt,
                    backgroundColor = Primary,
                    contentColor = OnPrimary,
                    cornerRadius = CornerFull,
                    modifier = Modifier.size(64.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Search Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Background)
                    .padding(horizontal = SpaceMarginMobile, vertical = SpaceSm)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SpaceSm)
                ) {
                    BrutalistTextField(
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.onSearchQueryChange(it) },
                        placeholder = "Search transactions...",
                        leadingIcon = Icons.Default.Search,
                        modifier = Modifier.weight(1f)
                    )
                    BrutalistButton(
                        onClick = {},
                        backgroundColor = Primary,
                        contentColor = OnPrimary,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(imageVector = Icons.Default.FilterList, contentDescription = null)
                        }
                    }
                }
            }

            // List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = SpaceMarginMobile),
                contentPadding = PaddingValues(bottom = 120.dp, top = SpaceSm),
                verticalArrangement = Arrangement.spacedBy(SpaceGutter)
            ) {
                uiState.groupedTransactions.forEach { (date, transactions) ->
                    item {
                        Text(
                            text = date,
                            style = HeadlineMd,
                            color = OnBackground,
                            modifier = Modifier.padding(vertical = SpaceXs)
                        )
                    }
                    items(transactions) { tx ->
                        val icon = when (tx.iconName) {
                            "restaurant" -> Icons.Default.Restaurant
                            "subway" -> Icons.Default.Subway
                            "shopping_cart" -> Icons.Default.ShoppingCart
                            "payments" -> Icons.Default.Payments
                            "bolt" -> Icons.Default.Bolt
                            else -> Icons.Default.Paid
                        }
                        TransactionRow(transaction = tx, icon = icon)
                    }
                }
            }
        }
    }
}
