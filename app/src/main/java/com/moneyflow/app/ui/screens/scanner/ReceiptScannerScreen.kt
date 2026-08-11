package com.moneyflow.app.ui.screens.scanner

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptScannerScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var isScanning by remember { mutableStateOf(false) }
    var scanCompleted by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Scanning animation loop
    val infiniteTransition = rememberInfiniteTransition(label = "scanLine")
    val scanLineProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scanLinePosition"
    )

    fun startScanning() {
        coroutineScope.launch {
            isScanning = true
            delay(3000) // simulation delay
            isScanning = false
            scanCompleted = true
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Surface)
                    .border(2.dp, BrutalistBorder)
                    .statusBarsPadding()
                    .padding(horizontal = SpaceMarginMobile),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(SpaceMd)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .background(SurfaceContainerLowest)
                        .border(2.dp, BrutalistBorder)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(text = "TRANSACTION DETAILS", style = HeadlineMd, color = OnBackground)
            }
        },
        containerColor = Background,
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Viewfinder / Camera preview simulator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .background(OnBackground.copy(alpha = 0.85f))
                        .border(BorderStroke(3.dp, OnBackground)),
                    contentAlignment = Alignment.Center
                ) {
                    // Frame guide
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.8f)
                            .border(4.dp, Primary)
                    ) {
                        // Align helper text
                        BrutalistCard(
                            backgroundColor = Surface,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(SpaceMd)
                        ) {
                            Text(
                                "Align receipt within frame",
                                style = LabelMd,
                                modifier = Modifier.padding(horizontal = SpaceMd, vertical = SpaceSm)
                            )
                        }

                        // Scanning green bar animation
                        if (isScanning) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val yOffset = scanLineProgress * size.height
                                drawLine(
                                    color = TertiaryFixed,
                                    start = Offset(0f, yOffset),
                                    end = Offset(size.width, yOffset),
                                    strokeWidth = 4.dp.toPx()
                                )
                            }
                        }
                    }
                }

                // Controls area
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                        .background(SurfaceContainerHigh)
                        .padding(SpaceLg),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BrutalistButton(
                        onClick = {},
                        backgroundColor = Surface,
                        contentColor = OnBackground,
                        modifier = Modifier.size(80.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.PhotoLibrary, contentDescription = null)
                            Text("Gallery", style = LabelMd.copy(fontSize = 10.sp))
                        }
                    }

                    BrutalistButton(
                        onClick = { startScanning() },
                        backgroundColor = Primary,
                        contentColor = OnPrimary,
                        cornerRadius = CornerFull,
                        modifier = Modifier.size(96.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    BrutalistButton(
                        onClick = {},
                        backgroundColor = Surface,
                        contentColor = OnBackground,
                        modifier = Modifier.size(80.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.FlashOn, contentDescription = null)
                            Text("Flash", style = LabelMd.copy(fontSize = 10.sp))
                        }
                    }
                }
            }

            // Scan Completion Dialog / Sheet Bottom
            if (scanCompleted) {
                ModalBottomSheet(
                    onDismissRequest = { scanCompleted = false },
                    sheetState = sheetState,
                    containerColor = Surface,
                    scrimColor = Color.Black.copy(alpha = 0.5f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceMarginMobile)
                            .padding(bottom = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(SpaceMd)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Scanned!",
                                style = HeadlineLgMobile.copy(color = OnBackground),
                                modifier = Modifier
                                    .background(TertiaryFixed)
                                    .border(2.dp, OnBackground)
                                    .padding(horizontal = SpaceSm)
                            )
                            IconButton(
                                onClick = { scanCompleted = false },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(ErrorContainer)
                                    .border(2.dp, OnBackground)
                            ) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = OnErrorContainer)
                            }
                        }

                        // Details Rows
                        BrutalistDetailRow(label = "Store", value = "Starbucks")
                        BrutalistDetailRow(label = "Total", value = "Rp 65.000", isTotal = true)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, OnBackground)
                                .background(SurfaceContainer)
                                .padding(SpaceSm),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("CATEGORY", style = LabelMd, color = OnSurfaceVariant)
                            Box(
                                modifier = Modifier
                                    .background(SecondaryContainer)
                                    .border(2.dp, OnBackground)
                                    .padding(horizontal = SpaceSm, vertical = SpaceXs)
                            ) {
                                Text("Food & Beverage", style = BodyLg.copy(color = OnSecondaryContainer))
                            }
                        }

                        BrutalistButton(
                            onClick = {
                                scanCompleted = false
                                onBack()
                            },
                            text = "SAVE RECEIPT",
                            backgroundColor = Primary,
                            contentColor = OnPrimary,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BrutalistDetailRow(label: String, value: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, OnBackground)
            .background(SurfaceContainer)
            .padding(SpaceSm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label.uppercase(), style = LabelMd, color = OnSurfaceVariant)
        Text(
            text = value,
            style = if (isTotal) DisplayLg.copy(fontSize = 32.sp) else HeadlineMd,
            color = if (isTotal) Primary else OnBackground
        )
    }
}
