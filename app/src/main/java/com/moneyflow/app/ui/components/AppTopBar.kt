package com.moneyflow.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneyflow.app.ui.theme.*

/**
 * The shared top app bar used on all main screens:
 * logo image placeholder + screen title + profile avatar.
 */
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Surface)
            .border(
                border = BorderStroke(BrutalistBorderWidth, BrutalistBorder),
                shape = RoundedCornerShape(0.dp)
            )
            .padding(horizontal = SpaceMarginMobile),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SpaceSm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo placeholder — MoneyFlow "M" mark
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Primary, RoundedCornerShape(CornerLg))
                    .border(BorderStroke(BrutalistBorderWidth, BrutalistBorder), RoundedCornerShape(CornerLg)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "M", style = LabelMd.copy(color = OnPrimary))
            }
            Text(text = title, style = HeadlineMd, color = OnBackground)
        }
        // Profile avatar placeholder
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(SecondaryFixed)
                .border(BorderStroke(2.dp, BrutalistBorder), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "S", style = LabelMd.copy(color = OnSecondaryFixed))
        }
    }
}
