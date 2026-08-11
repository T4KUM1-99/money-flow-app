package com.moneyflow.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.moneyflow.app.ui.theme.*

/**
 * Brutalist text field — thick border, flat background, no M3 ripple decoration.
 * The border turns [Primary] on focus (matching the HTML's focus:border-primary).
 */
@Composable
fun BrutalistTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    label: String? = null,
    leadingIcon: ImageVector? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    backgroundColor: Color = SurfaceContainerLowest,
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) Primary else BrutalistBorder

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(SpaceXs)) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = LabelMd,
                color = OnBackground
            )
        }
        Box(
            modifier = Modifier
                .brutalistShadow(shadowOffset = 4.dp)
                .border(BorderStroke(BrutalistBorderWidth, borderColor), RoundedCornerShape(CornerDefault))
                .clip(RoundedCornerShape(CornerDefault))
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = SpaceMd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(SpaceSm)
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = OnSurfaceVariant
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    textStyle = BodyLg.copy(color = OnBackground),
                    cursorBrush = SolidColor(Primary),
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation,
                    decorationBox = { innerTextField ->
                        Box {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = BodyLg.copy(color = Outline)
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                trailingContent?.invoke()
            }
        }
    }
}
