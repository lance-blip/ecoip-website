package za.co.quikle.lexio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import za.co.quikle.lexio.ui.screens.chat.ChatScreen
import za.co.quikle.lexio.ui.screens.home.HomeScreen
import za.co.quikle.lexio.ui.screens.library.LibraryDetailScreen
import za.co.quikle.lexio.ui.screens.library.LibraryScreen
import za.co.quikle.lexio.ui.screens.onboarding.OnboardingScreen
import za.co.quikle.lexio.ui.screens.premium.PremiumScreen
import za.co.quikle.lexio.ui.screens.profile.ProfileScreen
import za.co.quikle.lexio.ui.screens.scenario.ScenarioScreen
import za.co.quikle.lexio.ui.screens.settings.SettingsScreen
import za.co.quikle.lexio.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    onboardingCompleted: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateNext = {
                    val destination = if (onboardingCompleted) {
                        Screen.Home.route
                    } else {
                        Screen.Onboarding.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onOnboardingComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Chat.route,
            arguments = listOf(
                navArgument("conversationId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val conversationId = backStackEntry.arguments?.getString("conversationId")
            ChatScreen(navController = navController, conversationId = conversationId)
        }

        composable(Screen.Scenario.route) {
            ScenarioScreen(navController = navController)
        }

        composable(Screen.Library.route) {
            LibraryScreen(navController = navController)
        }

        composable(
            route = Screen.LibraryDetail.route,
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            LibraryDetailScreen(navController = navController, categoryId = categoryId)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(Screen.Premium.route) {
            PremiumScreen(navController = navController)
        }
    }
}
