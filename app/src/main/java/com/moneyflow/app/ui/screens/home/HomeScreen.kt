package com.moneyflow.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moneyflow.app.ui.components.AppTopBar
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.components.TransactionRow
import com.moneyflow.app.ui.theme.*
import com.moneyflow.app.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onScanReceipt: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Home") },
        containerColor = Background,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = SpaceMarginMobile)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 96.dp), // space for bottom nav
            verticalArrangement = Arrangement.spacedBy(SpaceGutter)
        ) {
            Spacer(modifier = Modifier.height(SpaceXs))

            // Balance Card
            BrutalistCard(
                backgroundColor = Primary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(SpaceLg)) {
                    Text(
                        text = "YOUR MONEY STATUS",
                        style = LabelMd,
                        color = OnPrimary.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(SpaceXs))
                    Text(
                        text = uiState.balance,
                        style = DisplayLg,
                        color = OnPrimary
                    )
                    Spacer(modifier = Modifier.height(SpaceMd))
                    Row(
                        modifier = Modifier
                            .background(TertiaryFixed, MaterialTheme.shapes.small)
                            .padding(horizontal = SpaceSm, vertical = SpaceXs),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(SpaceXs)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = OnTertiaryFixed,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = uiState.monthlyChange,
                            style = LabelMd,
                            color = OnTertiaryFixed
                        )
                    }
                }
            }

            // Quick Actions Grid (2x2 layout matching design)
            Column(verticalArrangement = Arrangement.spacedBy(SpaceMd)) {
                Row(horizontalArrangement = Arrangement.spacedBy(SpaceMd)) {
                    QuickActionButton(
                        onClick = {},
                        icon = Icons.Default.Remove,
                        iconColor = Error,
                        label = "Add Expense",
                        backgroundColor = SecondaryFixed,
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionButton(
                        onClick = {},
                        icon = Icons.Default.Add,
                        iconColor = TertiaryContainer,
                        label = "Add Income",
                        backgroundColor = TertiaryFixed,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(SpaceMd)) {
                    QuickActionButton(
                        onClick = onScanReceipt,
                        icon = Icons.Default.ReceiptLong,
                        iconColor = Primary,
                        label = "Scan Receipt",
                        backgroundColor = SurfaceContainerHigh,
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionButton(
                        onClick = {},
                        icon = Icons.Default.AccountBalance,
                        iconColor = Primary,
                        label = "Add Asset",
                        backgroundColor = InversePrimary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Recent Transactions List
            Column(verticalArrangement = Arrangement.spacedBy(SpaceMd)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Recent Flow", style = HeadlineMd, color = OnBackground)
                    TextButton(onClick = {}) {
                        Text(
                            "View All",
                            style = LabelMd,
                            color = Primary,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                uiState.recentTransactions.forEach { tx ->
                    val icon = when (tx.iconName) {
                        "local_cafe" -> Icons.Default.LocalCafe
                        "work" -> Icons.Default.Work
                        "directions_car" -> Icons.Default.DirectionsCar
                        else -> Icons.Default.Paid
                    }
                    TransactionRow(transaction = tx, icon = icon)
                }
            }
        }
    }
}

@Composable
fun QuickActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    iconColor: Color,
    label: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    BrutalistButton(
        onClick = onClick,
        backgroundColor = backgroundColor,
        contentColor = OnBackground,
        cornerRadius = CornerLg,
        modifier = modifier.height(120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpaceSm),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Surface, CircleShape)
                    .border(2.dp, BrutalistBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = label,
                style = ButtonText.copy(fontSize = 14.sp),
                color = OnBackground
            )
        }
    }
}
