package com.moneyflow.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.moneyflow.app.data.model.Transaction
import com.moneyflow.app.data.model.TransactionType
import com.moneyflow.app.ui.theme.*

/**
 * Single transaction list row — icon + merchant name / category + amount.
 * Background color varies per transaction (matching the brutalist style).
 */
@Composable
fun TransactionRow(
    transaction: Transaction,
    modifier: Modifier = Modifier,
    icon: ImageVector,
) {
    val amountColor = if (transaction.type == TransactionType.INCOME)
        TertiaryContainer else Error

    Row(
        modifier = modifier
            .fillMaxWidth()
            .brutalistShadow(shadowOffset = 3.dp)
            .border(BorderStroke(BrutalistBorderWidth, BrutalistBorder), RoundedCornerShape(CornerXl))
            .clip(RoundedCornerShape(CornerXl))
            .background(Surface)
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
                    .border(BorderStroke(2.dp, BrutalistBorder), RoundedCornerShape(CornerLg))
                    .clip(RoundedCornerShape(CornerLg))
                    .background(transaction.iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = OnBackground,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column {
                Text(text = transaction.title, style = BodyLg, color = OnBackground)
                Text(
                    text = transaction.category.uppercase(),
                    style = LabelMd,
                    color = OnSurfaceVariant
                )
            }
        }
        Text(
            text = transaction.amount,
            style = HeadlineMd,
            color = amountColor
        )
    }
}
