package com.moneyflow.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.moneyflow.app.ui.theme.*
import kotlin.math.roundToInt

/**
 * Brutalist button with press animation:
 * - resting: hard 4dp shadow
 * - pressed: translates +4dp toward shadow, shadow disappears → "sinking" effect
 */
@Composable
fun BrutalistButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    backgroundColor: Color = Primary,
    contentColor: Color = OnPrimary,
    borderColor: Color = BrutalistBorder,
    cornerRadius: Dp = CornerDefault,
    shadowOffset: Dp = 4.dp,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val offsetAnim by animateFloatAsState(
        targetValue = if (isPressed) shadowOffset.value else 0f,
        label = "brutalistBtnOffset"
    )

    Box(
        modifier = modifier
            // Shadow layer (only visible when not pressed)
            .then(
                if (!isPressed) Modifier.brutalistShadow(
                    shadowColor = borderColor,
                    shadowOffset = shadowOffset
                )
                else Modifier
            )
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .offset { IntOffset(offsetAnim.roundToInt(), offsetAnim.roundToInt()) }
                .fillMaxWidth(),
            interactionSource = interactionSource,
            shape = RoundedCornerShape(cornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor
            ),
            border = BorderStroke(BrutalistBorderWidth, borderColor),
            contentPadding = PaddingValues(horizontal = SpaceMd, vertical = SpaceMd),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            if (content != null) {
                content()
            } else {
                Text(
                    text = text,
                    style = ButtonText,
                    color = contentColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
