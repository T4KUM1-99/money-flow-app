package com.moneyflow.app.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
                    .padding(top = 48.dp),
                verticalArrangement = Arrangement.spacedBy(SpaceMd)
            ) {
                // Logo box matching HTML
                BrutalistCard(
                    modifier = Modifier.size(128.dp),
                    backgroundColor = SurfaceContainer,
                    cornerRadius = CornerLg
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("MF", style = DisplayLg.copy(fontSize = 32.sp))
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(SpaceXs)
                ) {
                    Text("Welcome Back!", style = HeadlineLgMobile, color = OnBackground)
                }

                Spacer(modifier = Modifier.height(SpaceMd))

                // Inputs
                BrutalistTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "you@example.com",
                    label = "Email Address",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(SpaceSm))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PASSWORD",
                            style = LabelMd,
                            color = OnBackground
                        )
                        TextButton(onClick = { /* Forgot pass action */ }) {
                            Text(
                                "Forgot?",
                                style = LabelMd,
                                color = Primary,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
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
                }

                Spacer(modifier = Modifier.height(SpaceMd))

                BrutalistButton(
                    onClick = onSignIn,
                    text = "SIGN IN",
                    backgroundColor = Primary,
                    contentColor = OnPrimary,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(SpaceLg))

                // Social Logins
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SpaceSm)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(OnBackground)
                    )
                    Text("OR CONTINUE WITH", style = LabelMd, color = OnSurfaceVariant)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(OnBackground)
                    )
                }

                Spacer(modifier = Modifier.height(SpaceSm))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SpaceGutter)
                ) {
                    BrutalistButton(
                        onClick = onSignIn,
                        text = "Google",
                        backgroundColor = Surface,
                        contentColor = OnBackground,
                        modifier = Modifier.weight(1f)
                    )
                    BrutalistButton(
                        onClick = onSignIn,
                        text = "Apple",
                        backgroundColor = OnBackground,
                        contentColor = OnPrimary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Footer Link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SpaceLg),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account?", style = BodyMd, color = OnBackground)
                TextButton(onClick = onGoToSignUp) {
                    Text(
                        "Sign Up",
                        style = LabelMd,
                        color = Primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}
