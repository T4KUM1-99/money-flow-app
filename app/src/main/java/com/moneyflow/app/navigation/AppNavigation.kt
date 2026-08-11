package com.moneyflow.app.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moneyflow.app.ui.components.BottomNavBar
import com.moneyflow.app.ui.screens.assets.AssetsScreen
import com.moneyflow.app.ui.screens.auth.SignInScreen
import com.moneyflow.app.ui.screens.auth.SignUpScreen
import com.moneyflow.app.ui.screens.budget.BudgetScreen
import com.moneyflow.app.ui.screens.home.HomeScreen
import com.moneyflow.app.ui.screens.profile.ProfileScreen
import com.moneyflow.app.ui.screens.scanner.ReceiptScannerScreen
import com.moneyflow.app.ui.screens.transactions.TransactionsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Routes that show the bottom nav bar
    val mainRoutes = setOf(
        Screen.Home.route, Screen.Transactions.route,
        Screen.Budget.route, Screen.Assets.route, Screen.Profile.route
    )
    val showBottomBar = currentRoute in mainRoutes

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Screen.SignIn.route,
            modifier = Modifier.fillMaxSize()
        ) {
            // ── Auth graph ────────────────────────────────────────────────────
            composable(Screen.SignIn.route) {
                SignInScreen(
                    onSignIn = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.SignIn.route) { inclusive = true }
                        }
                    },
                    onGoToSignUp = { navController.navigate(Screen.SignUp.route) }
                )
            }
            composable(Screen.SignUp.route) {
                SignUpScreen(
                    onSignUp = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.SignIn.route) { inclusive = true }
                        }
                    },
                    onGoToSignIn = { navController.popBackStack() }
                )
            }

            // ── Main bottom-nav graph ─────────────────────────────────────────
            composable(Screen.Home.route) {
                HomeScreen(
                    onScanReceipt = { navController.navigate(Screen.ReceiptScanner.route) }
                )
            }
            composable(Screen.Transactions.route) {
                TransactionsScreen(
                    onScanReceipt = { navController.navigate(Screen.ReceiptScanner.route) }
                )
            }
            composable(Screen.Budget.route) {
                BudgetScreen()
            }
            composable(Screen.Assets.route) {
                AssetsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogOut = {
                        navController.navigate(Screen.SignIn.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // ── Detail screens ────────────────────────────────────────────────
            composable(Screen.ReceiptScanner.route) {
                ReceiptScannerScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // Bottom nav overlay at the bottom
        if (showBottomBar) {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(androidx.compose.ui.Alignment.BottomCenter)
            )
        }
    }
}
