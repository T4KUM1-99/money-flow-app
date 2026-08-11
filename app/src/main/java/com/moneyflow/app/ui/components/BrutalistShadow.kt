package com.moneyflow.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneyflow.app.ui.theme.BrutalistShadowColor

/**
 * Draws the signature neo-brutalist hard drop-shadow behind the composable.
 *
 * The shadow is a solid black/dark rectangle offset by [shadowOffset] in both x and y —
 * no blur, no spread, pure brutalist style.
 */
fun Modifier.brutalistShadow(
    shadowColor: Color = BrutalistShadowColor,
    shadowOffset: Dp = 4.dp,
    borderColor: Color = BrutalistShadowColor,
    borderWidth: Dp = 1.5.dp,
): Modifier = this.drawBehind {
    val offsetPx = shadowOffset.toPx()
    // Draw shadow rectangle slightly offset
    drawRect(
        color = shadowColor,
        topLeft = Offset(offsetPx, offsetPx),
        size = size,
    )
}

/**
 * Animates the press state of a brutalist element:
 * - on press: translate +4dp (towards the shadow), shadow disappears (handled by parent)
 * - on release: snap back
 *
 * Returns [isPressed] state for the caller to conditionally apply the shadow modifier.
 */
@Composable
fun rememberBrutalistPressState(): Pair<Boolean, Modifier> {
    var isPressed by remember { mutableStateOf(false) }
    val translation by animateFloatAsState(
        targetValue = if (isPressed) 4f else 0f,
        label = "brutalistPress"
    )
    val modifier = Modifier
        .offset(x = translation.dp, y = translation.dp)
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                }
            )
        }
    return isPressed to modifier
}
