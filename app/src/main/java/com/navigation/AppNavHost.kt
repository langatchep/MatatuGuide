package com.joyline.matatuguide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

import com.joyline.matatuguide.ui.screens.home.HomeScreen
import com.joyline.matatuguide.ui.screens.auth.LoginScreen
import com.joyline.matatuguide.ui.screens.auth.RegisterScreen
import com.joyline.matatuguide.ui.screens.details.RouteDetailsScreen
import com.joyline.matatuguide.ui.screens.results.ResultsScreen

import com.joyline.matatuguide.ui.screens.splash.SplashScreen
import com.joyline.matatuguide.ui.screens.maps.MapScreen
import com.joyline.matatuguide.ui.screens.saved.SavedRoutesScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {

        // 🔵 SPLASH
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        // 🏠 HOME
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        // 🔐 LOGIN
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        // 📝 REGISTER
        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        // 💾 SAVED
        composable(Routes.SAVED) {
            SavedRoutesScreen()
        }

        // 📍 RESULTS
        composable(
            route = Routes.RESULTS,
            arguments = listOf(
                navArgument("from") { type = NavType.StringType },
                navArgument("to") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""

            ResultsScreen(
                navController = navController,
                from = from,
                to = to
            )
        }

        // 🚏 DETAILS
        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("start") { type = NavType.StringType },
                navArgument("end") { type = NavType.StringType },
                navArgument("stages") { type = NavType.StringType },
                navArgument("fare") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            RouteDetailsScreen(
                navController = navController,
                start = backStackEntry.arguments?.getString("start") ?: "",
                end = backStackEntry.arguments?.getString("end") ?: "",
                stages = backStackEntry.arguments?.getString("stages") ?: "",
                fare = backStackEntry.arguments?.getString("fare") ?: ""
            )
        }

        // 🗺️ MAPS (FIXED)
        composable(
            route = Routes.MAPS,
            arguments = listOf(
                navArgument("start") { type = NavType.StringType },
                navArgument("end") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            MapScreen(
                start = backStackEntry.arguments?.getString("start") ?: "",
                end = backStackEntry.arguments?.getString("end") ?: ""
            )
        }
    }
}