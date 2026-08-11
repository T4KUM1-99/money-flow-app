package com.moneyflow.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyflow.app.ui.theme.*

/**
 * The fundamental brutalist card: thick border + hard offset shadow + flat background.
 *
 * Usage:
 * ```
 * BrutalistCard(backgroundColor = Primary) {
 *     Text("Content")
 * }
 * ```
 */
@Composable
fun BrutalistCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Surface,
    borderColor: Color = BrutalistBorder,
    cornerRadius: Dp = CornerXl,
    shadowOffset: Dp = 4.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .brutalistShadow(shadowColor = borderColor, shadowOffset = shadowOffset)
            .border(
                border = BorderStroke(BrutalistBorderWidth, borderColor),
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor),
        content = content
    )
}
