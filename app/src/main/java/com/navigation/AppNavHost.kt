package com.joyline.matatuguide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.joyline.matatuguide.ui.screens.home.HomeScreen
import com.joyline.matatuguide.ui.screens.auth.LoginScreen
import com.joyline.matatuguide.ui.screens.auth.RegisterScreen
import com.joyline.matatuguide.ui.screens.results.ResultsScreen
import com.joyline.matatuguide.ui.screens.details.RouteDetailsScreen
import com.joyline.matatuguide.ui.screens.saved.SavedRoutesScreen
import com.joyline.matatuguide.ui.screens.splash.SplashScreen
import com.joyline.matatuguide.ui.screens.maps.MapScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = ROUT_SPLASH,
        modifier = modifier
    ) {

        // ---------------- SPLASH ----------------
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        // ---------------- HOME ----------------
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        // ---------------- LOGIN ----------------
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        // ---------------- REGISTER ----------------
        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        // ---------------- SAVED ROUTES ----------------
        composable(ROUT_SAVED) {
            SavedRoutesScreen()
        }

        // ---------------- RESULTS ----------------
        composable(ROUT_RESULTS) { backStackEntry ->

            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""

            ResultsScreen(
                navController = navController,
                from = from,
                to = to
            )
        }

        // ---------------- DETAILS ----------------
        composable(ROUT_DETAILS) { backStackEntry ->

            val start = backStackEntry.arguments?.getString("start") ?: ""
            val end = backStackEntry.arguments?.getString("end") ?: ""
            val stages = backStackEntry.arguments?.getString("stages") ?: ""
            val fare = backStackEntry.arguments?.getString("fare") ?: ""

            RouteDetailsScreen(
                navController = navController,
                start = start,
                end = end,
                stages = stages,
                fare = fare
            )
        }

        // ---------------- MAP ----------------
        val ROUT_MAP = ""
        composable(ROUT_MAP) { backStackEntry ->

            val start = backStackEntry.arguments?.getString("start") ?: ""
            val end = backStackEntry.arguments?.getString("end") ?: ""

            MapScreen(
                start = start,
                end = end
            )
        }
    }
}