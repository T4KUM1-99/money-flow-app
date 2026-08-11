package com.moneyflow.app.ui.screens.assets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moneyflow.app.data.model.AllocationSlice
import com.moneyflow.app.data.model.Asset
import com.moneyflow.app.ui.components.AppTopBar
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.theme.*
import com.moneyflow.app.viewmodel.AssetsViewModel

@Composable
fun AssetsScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Assets") },
        containerColor = Background,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = SpaceMarginMobile)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(SpaceGutter)
        ) {
            Spacer(modifier = Modifier.height(SpaceXs))

            // Portfolio Overview Card
            BrutalistCard(
                backgroundColor = SurfaceContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(SpaceMd)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Total Assets", style = HeadlineMd, color = OnSurface)
                        Icon(
                            imageVector = Icons.Default.ShowChart,
                            contentDescription = null,
                            tint = Primary
                        )
                    }

                    Text(text = uiState.totalAssets, style = DisplayLg, color = OnSurface)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(SpaceXs)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            contentDescription = null,
                            tint = TertiaryFixed,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = uiState.monthlyChange,
                            style = LabelMd,
                            color = TertiaryContainer
                        )
                        Text(text = "this month", style = BodyMd, color = OnSurfaceVariant)
                    }

                    Spacer(modifier = Modifier.height(SpaceMd))

                    // Sparkline
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        val points = uiState.sparkline
                        if (points.isNotEmpty()) {
                            val path = Path().apply {
                                val first = points.first()
                                moveTo(first.first * size.width, (1f - first.second) * size.height)
                                for (i in 1 until points.size) {
                                    val p = points[i]
                                    lineTo(p.first * size.width, (1f - p.second) * size.height)
                                }
                            }
                            drawPath(path = path, color = Primary, style = Stroke(width = 4.dp.toPx()))
                        }
                    }
                }
            }

            // Allocation Breakdown Card
            BrutalistCard(
                backgroundColor = SecondaryFixed,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(SpaceMd)) {
                    Text(text = "Allocation", style = HeadlineMd, color = OnSecondaryFixed)

                    Spacer(modifier = Modifier.height(SpaceMd))

                    // Stacked bar chart
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .border(BorderStroke(2.dp, OnBackground))
                    ) {
                        uiState.allocation.forEach { slice ->
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(slice.percentage)
                                    .background(slice.color)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(SpaceMd))

                    // Legend grid
                    Column(verticalArrangement = Arrangement.spacedBy(SpaceSm)) {
                        uiState.allocation.chunked(2).forEach { chunk ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                chunk.forEach { slice ->
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(SpaceXs)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(16.dp)
                                                .background(slice.color)
                                                .border(2.dp, OnBackground)
                                        )
                                        Text(
                                            text = "${slice.label} ${(slice.percentage * 100).toInt()}%",
                                            style = LabelMd,
                                            color = OnSecondaryFixed
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // My Assets Section
            Column(verticalArrangement = Arrangement.spacedBy(SpaceMd)) {
                Text(text = "My Assets", style = HeadlineMd, color = OnBackground)

                uiState.assets.forEach { asset ->
                    val icon = when (asset.iconName) {
                        "account_balance" -> Icons.Default.AccountBalance
                        "show_chart" -> Icons.Default.ShowChart
                        "diamond" -> Icons.Default.Diamond
                        else -> Icons.Default.AccountBalance
                    }
                    AssetCardRow(asset = asset, icon = icon)
                }

                BrutalistButton(
                    onClick = {},
                    backgroundColor = Primary,
                    contentColor = OnPrimary,
                    cornerRadius = CornerXl,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(SpaceSm)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text("ADD ASSET", style = ButtonText)
                    }
                }
            }
        }
    }
}

@Composable
fun AssetCardRow(
    asset: Asset,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    BrutalistCard(
        backgroundColor = Surface,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceMd),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceMd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(asset.iconBgColor, CircleShape)
                        .border(2.dp, BrutalistBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = OnBackground)
                }
                Column {
                    Text(text = asset.name, style = HeadlineMd.copy(fontSize = 20.sp), color = OnSurface)
                    Text(text = asset.type.uppercase(), style = LabelMd, color = OnSurfaceVariant)
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = asset.value, style = HeadlineMd.copy(fontSize = 20.sp), color = OnSurface)
                Text(
                    text = asset.changePercent,
                    style = LabelMd,
                    color = if (asset.isPositive) TertiaryContainer else Error
                )
            }
        }
    }
}
