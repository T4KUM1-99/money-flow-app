package com.moneyflow.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.api.Monitoring
import com.moneyflow.app.navigation.Screen
import com.moneyflow.app.ui.theme.*

data class NavItem(
    val screen: Screen,
    val icon: ImageVector,
    val label: String,
)

val navItems = listOf(
    NavItem(Screen.Home,         Icons.Default.Home,            "HOME"),
    NavItem(Screen.Transactions, Icons.Default.ReceiptLong,     "TRANS"),
    NavItem(Screen.Budget,       Icons.Default.AccountBalanceWallet, "BUDGET"),
    NavItem(Screen.Assets,       Icons.Default.Monitor,      "ASSETS"),
    NavItem(Screen.Profile,      Icons.Default.Person,          "PROFILE"),
)

/**
 * 5-tab bottom navigation bar.
 * Active tab: secondary-container background + brutalist offset shadow.
 * Tabs are separated by thick vertical borders.
 */
@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Surface)
            .border(
                BorderStroke(BrutalistBorderWidth, BrutalistBorder),
                RoundedCornerShape(0.dp)
            )
    ) {
        navItems.forEachIndexed { index, item ->
            val isSelected = currentRoute == item.screen.route
            val bgColor = if (isSelected) SecondaryContainer else Color.Transparent

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(bgColor)
                    .then(
                        if (index < navItems.lastIndex)
                            Modifier.border(
                                start = BorderStroke(BrutalistBorderWidth, BrutalistBorder)
                                    .let { null } ?: BorderStroke(0.dp, Color.Transparent),
                                end = BorderStroke(BrutalistBorderWidth, BrutalistBorder),
                            )
                        else Modifier
                    )
                    .clickable { onNavigate(item.screen) },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = OnBackground,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = item.label,
                        style = LabelMd.copy(fontSize = LabelMd.fontSize * 0.85f),
                        color = OnBackground,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

// Workaround extension for right/end border only (simulating per-side borders in Compose)
private fun Modifier.border(start: BorderStroke?, end: BorderStroke?): Modifier {
    return if (end != null) {
        this.padding(end = end.width)
            .border(
                BorderStroke(end.width, end.brush),
                RoundedCornerShape(0.dp)
            )
    } else this
}
