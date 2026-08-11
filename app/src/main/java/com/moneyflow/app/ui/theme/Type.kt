package com.moneyflow.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Inter is included via downloadable fonts in the resources.
// Using system default as fallback since we bundle via GoogleFonts.
val InterFamily = FontFamily.Default   // replaced in Theme.kt with actual Inter
val SpaceGroteskFamily = FontFamily.Default

// ── Text Styles matching Stitch design tokens ─────────────────────────────────

// display-lg: 48sp / 900 / tracking -0.04em → used for balance amounts
val DisplayLg = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Black,
    fontSize = 48.sp,
    lineHeight = 56.sp,
    letterSpacing = (-1.92).sp  // -0.04em of 48sp
)

// headline-lg: 32sp / 800 / tracking -0.02em
val HeadlineLg = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = (-0.64).sp
)

// headline-lg-mobile: 28sp / 800 / tracking -0.02em
val HeadlineLgMobile = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 28.sp,
    lineHeight = 34.sp,
    letterSpacing = (-0.56).sp
)

// headline-md: 24sp / 700
val HeadlineMd = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)

// body-lg: 18sp / 500
val BodyLg = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 28.sp
)

// body-md: 16sp / 400
val BodyMd = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

// label-md: 14sp / 600 — uses Space Grotesk, often UPPERCASE
val LabelMd = TextStyle(
    fontFamily = SpaceGroteskFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

// button-text: 18sp / 700
val ButtonText = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 24.sp
)

// Material 3 Typography — maps Stitch styles to M3 roles
val MoneyFlowTypography = Typography(
    displayLarge = DisplayLg,
    headlineLarge = HeadlineLg,
    headlineMedium = HeadlineLgMobile,
    headlineSmall = HeadlineMd,
    bodyLarge = BodyLg,
    bodyMedium = BodyMd,
    labelMedium = LabelMd,
    labelSmall = LabelMd.copy(fontSize = 12.sp)
)
