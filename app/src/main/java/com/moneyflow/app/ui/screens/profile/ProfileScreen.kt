package com.moneyflow.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneyflow.app.ui.components.AppTopBar
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.components.brutalistShadow
import com.moneyflow.app.ui.theme.*

@Composable
fun ProfileScreen(
    onLogOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    var darkModeEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppTopBar(title = "Profile") },
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

            // Profile Header Card
            BrutalistCard(
                backgroundColor = PrimaryFixed,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SpaceLg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerLowest)
                            .border(2.dp, OnBackground, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "SJ", style = DisplayLg.copy(fontSize = 32.sp))
                    }

                    Spacer(modifier = Modifier.height(SpaceSm))

                    Text(text = "Sarah Jenkins", style = HeadlineLgMobile, color = OnPrimaryFixed)
                    Text(text = "@sarahj", style = BodyLg, color = OnPrimaryFixedVariant)
                }
            }

            // Streak metrics grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SpaceGutter)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .brutalistShadow(shadowOffset = 4.dp)
                        .border(2.dp, OnBackground)
                        .background(TertiaryFixed)
                        .padding(SpaceMd)
                ) {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, tint = OnBackground)
                    Spacer(modifier = Modifier.height(SpaceXs))
                    Text(text = "MEMBER SINCE", style = LabelMd, color = OnTertiaryFixedVariant)
                    Text(text = "Oct 2022", style = HeadlineMd, color = OnBackground)
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .brutalistShadow(shadowOffset = 4.dp)
                        .border(2.dp, OnBackground)
                        .background(SecondaryFixed)
                        .padding(SpaceMd)
                ) {
                    Icon(imageVector = Icons.Default.LocalFireDepartment, contentDescription = null, tint = OnBackground)
                    Spacer(modifier = Modifier.height(SpaceXs))
                    Text(text = "SAVINGS STREAK", style = LabelMd, color = OnSecondaryFixedVariant)
                    Text(text = "14 Weeks", style = HeadlineMd, color = OnBackground)
                }
            }

            // Settings sections
            SettingsSection(
                title = "Account",
                items = listOf(
                    SettingsItem("Personal Info", Icons.Default.Person),
                    SettingsItem("Security", Icons.Default.Lock),
                    SettingsItem("Connected Banks", Icons.Default.AccountBalance)
                )
            )

            // Preferences
            Column(verticalArrangement = Arrangement.spacedBy(SpaceSm)) {
                Text(text = "Preferences", style = HeadlineMd, color = OnBackground)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .brutalistShadow(shadowOffset = 4.dp)
                        .border(2.dp, OnBackground)
                        .background(SurfaceContainerLowest)
                ) {
                    SettingsRow(label = "Notifications", icon = Icons.Default.Notifications)
                    Divider()
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
                            Icon(imageVector = Icons.Default.DarkMode, contentDescription = null, tint = OnBackground)
                            Text(text = "Dark Mode", style = BodyLg, color = OnBackground)
                        }
                        Switch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = OnBackground,
                                checkedTrackColor = Primary,
                                uncheckedThumbColor = OnBackground,
                                uncheckedTrackColor = SurfaceVariant
                            )
                        )
                    }
                    Divider()
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
                            Icon(imageVector = Icons.Default.Language, contentDescription = null, tint = OnBackground)
                            Text(text = "Language", style = BodyLg, color = OnBackground)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(SpaceXs),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "English", style = BodyMd, color = OnSurfaceVariant)
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }

            // Logout Button
            BrutalistButton(
                onClick = onLogOut,
                backgroundColor = SecondaryContainer,
                contentColor = OnSecondaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SpaceSm)
                ) {
                    Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                    Text(text = "LOG OUT", style = ButtonText)
                }
            }
        }
    }
}

data class SettingsItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun SettingsSection(title: String, items: List<SettingsItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(SpaceSm)) {
        Text(text = title, style = HeadlineMd, color = OnBackground)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .brutalistShadow(shadowOffset = 4.dp)
                .border(2.dp, OnBackground)
                .background(SurfaceContainerLowest)
        ) {
            items.forEachIndexed { index, item ->
                SettingsRow(label = item.label, icon = item.icon)
                if (index < items.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun SettingsRow(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(SpaceMd),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SpaceMd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = OnBackground)
            Text(text = label, style = BodyLg, color = OnBackground)
        }
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(OnBackground)
    )
}
