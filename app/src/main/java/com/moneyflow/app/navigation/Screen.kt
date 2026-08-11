package com.moneyflow.app.navigation

sealed class Screen(val route: String) {
    // Auth graph
    object SignIn  : Screen("sign_in")
    object SignUp  : Screen("sign_up")

    // Main graph — bottom nav tabs
    object Home         : Screen("home")
    object Transactions : Screen("transactions")
    object Budget       : Screen("budget")
    object Assets       : Screen("assets")
    object Profile      : Screen("profile")

    // Detail screens
    object ReceiptScanner : Screen("receipt_scanner")
}
