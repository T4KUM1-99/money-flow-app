package com.moneyflow.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneyflow.app.ui.components.BrutalistButton
import com.moneyflow.app.ui.components.BrutalistCard
import com.moneyflow.app.ui.components.BrutalistTextField
import com.moneyflow.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUp: () -> Unit,
    onGoToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }

    val passwordStrength = remember(password) {
        when {
            password.isEmpty() -> 0
            password.length < 6 -> 1
            password.any { it.isUpperCase() } && password.any { it.isDigit() } -> 3
            else -> 2
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceMarginMobile)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(SpaceMd)
            ) {
                // Logo card
                BrutalistCard(
                    modifier = Modifier.size(64.dp),
                    backgroundColor = SurfaceContainer,
                    cornerRadius = CornerLg
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("MF", style = DisplayLg.copy(fontSize = 20.sp))
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(SpaceXs)
                ) {
                    Text("Create Account", style = DisplayLg.copy(fontSize = 32.sp), color = OnBackground)
                    Text("Join MoneyFlow today.", style = BodyLg, color = OnSurfaceVariant)
                }

                Spacer(modifier = Modifier.height(SpaceXs))

                BrutalistTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "John Doe",
                    label = "Full Name",
                    modifier = Modifier.fillMaxWidth()
                )

                BrutalistTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "you@example.com",
                    label = "Email Address",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "PASSWORD",
                        style = LabelMd,
                        color = OnBackground,
                        modifier = Modifier.padding(bottom = SpaceXs)
                    )
                    BrutalistTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "••••••••",
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingContent = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(SpaceSm))

                    // Strength Meter
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(SpaceXs)
                    ) {
                        repeat(3) { index ->
                            val color = when {
                                index >= passwordStrength -> SurfaceVariant
                                passwordStrength == 1 -> Error
                                passwordStrength == 2 -> TertiaryFixed
                                else -> Primary
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(8.dp)
                                    .background(color)
                                    .border(2.dp, OnBackground)
                            )
                        }
                    }
                    val strengthText = when (passwordStrength) {
                        0 -> "Enter password"
                        1 -> "Weak"
                        2 -> "Good"
                        else -> "Strong"
                    }
                    Text(
                        text = strengthText,
                        style = LabelMd.copy(fontSize = 12.sp),
                        color = OnSurfaceVariant,
                        modifier = Modifier.align(Alignment.End).padding(top = SpaceXs)
                    )
                }

                // Terms agreement checkbox matching custom HTML checkbox
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SpaceXs),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = agreeToTerms,
                        onCheckedChange = { agreeToTerms = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Primary,
                            uncheckedColor = SurfaceContainerLowest,
                            checkmarkColor = OnPrimary
                        )
                    )
                    Text(
                        text = "I agree to the Terms and Privacy Policy",
                        style = BodyMd,
                        color = OnBackground
                    )
                }

                Spacer(modifier = Modifier.height(SpaceSm))

                BrutalistButton(
                    onClick = onSignUp,
                    text = "CREATE ACCOUNT",
                    backgroundColor = TertiaryFixed,
                    contentColor = OnBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Footer Link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SpaceLg),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account?", style = BodyMd, color = OnBackground)
                TextButton(onClick = onGoToSignIn) {
                    Text(
                        "Sign In",
                        style = LabelMd,
                        color = Primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}
