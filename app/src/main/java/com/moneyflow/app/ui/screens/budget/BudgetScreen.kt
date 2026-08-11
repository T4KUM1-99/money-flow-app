package com.moneyflow.app.ui.screens.budget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.moneyflow.app.data.model.BudgetCategory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moneyflow.app.ui.components.AppTopBar
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.theme.*
import com.moneyflow.app.viewmodel.BudgetViewModel

@Composable
fun BudgetScreen(
    modifier: Modifier = Modifier,
    viewModel: BudgetViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Budget") },
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

            // Overall Budget Card
            BrutalistCard(
                backgroundColor = SurfaceContainerHigh,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(SpaceMd)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(text = uiState.month, style = HeadlineLgMobile, color = OnBackground)
                            Text(text = "Overall Budget", style = BodyMd, color = OnSurfaceVariant)
                        }
                        BrutalistButton(
                            onClick = {},
                            backgroundColor = SecondaryContainer,
                            contentColor = OnBackground,
                            cornerRadius = CornerFull,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(SpaceMd))

                    // Progress Donut (faithful recreation of the HTML SVG)
                    Box(
                        modifier = Modifier
                            .size(192.dp)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            // Outer ring
                            drawCircle(color = OnBackground, radius = 92.dp.toPx(), style = Stroke(2.dp.toPx()))
                            // Inner ring
                            drawCircle(color = OnBackground, radius = 68.dp.toPx(), style = Stroke(2.dp.toPx()))
                            // Background track
                            drawArc(
                                color = SurfaceVariant,
                                startAngle = -90f,
                                sweepAngle = 360f,
                                useCenter = false,
                                style = Stroke(24.dp.toPx(), cap = StrokeCap.Square)
                            )
                            // Used progress
                            drawArc(
                                color = Primary,
                                startAngle = -90f,
                                sweepAngle = uiState.usedPercent * 360f,
                                useCenter = false,
                                style = Stroke(24.dp.toPx(), cap = StrokeCap.Square)
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${(uiState.usedPercent * 100).toInt()}%",
                                style = HeadlineLgMobile,
                                color = OnBackground
                            )
                            Text(
                                text = "USED",
                                style = LabelMd,
                                color = OnSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(SpaceMd))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, OnBackground))
                            .padding(top = SpaceMd),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("SPENT", style = LabelMd, color = OnSurfaceVariant)
                            Text(uiState.totalSpent, style = HeadlineMd, color = OnBackground)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("TOTAL LIMIT", style = LabelMd, color = OnSurfaceVariant)
                            Text(uiState.totalLimit, style = HeadlineMd, color = OnBackground)
                        }
                    }
                }
            }

            // Categories Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Categories", style = HeadlineMd, color = OnBackground)
                BrutalistButton(
                    onClick = {},
                    text = "Add New",
                    backgroundColor = Primary,
                    contentColor = OnPrimary,
                    cornerRadius = CornerLg,
                    modifier = Modifier.wrapContentSize()
                )
            }

            uiState.categories.forEach { category ->
                val icon = when (category.iconName) {
                    "restaurant" -> Icons.Default.Restaurant
                    "directions_car" -> Icons.Default.DirectionsCar
                    "sports_esports" -> Icons.Default.SportsEsports
                    else -> Icons.Default.Restaurant
                }
                CategoryProgressRow(category = category, icon = icon)
            }
        }
    }
}

@Composable
fun CategoryProgressRow(
    category: BudgetCategory,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    BrutalistCard(
        backgroundColor = Surface,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(SpaceMd)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpaceSm),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(category.iconBgColor, CircleShape)
                            .border(2.dp, BrutalistBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = icon, contentDescription = null, tint = OnBackground)
                    }
                    Text(text = category.name, style = BodyLg, color = OnSurface)
                }
                Text(text = "${(category.percentage * 100).toInt()}%", style = HeadlineMd, color = OnSurface)
            }

            Spacer(modifier = Modifier.height(SpaceSm))

            // Progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(SurfaceContainerLowest)
                    .border(2.dp, OnBackground)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(category.percentage)
                        .background(category.progressColor)
                        .border(
                            BorderStroke(
                                width = 2.dp,
                                color = OnBackground
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(SpaceSm))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = category.spent,
                    style = BodyMd,
                    color = if (category.percentage >= 0.85f) Error else OnSurface
                )
                Text(
                    text = "of ${category.total}",
                    style = LabelMd,
                    color = OnSurfaceVariant
                )
            }
        }
    }
}
