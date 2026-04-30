package za.co.quikle.lexio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.map
import za.co.quikle.lexio.navigation.BottomNavBar
import za.co.quikle.lexio.navigation.NavGraph
import za.co.quikle.lexio.navigation.Screen
import za.co.quikle.lexio.ui.theme.LexioTheme
import za.co.quikle.lexio.util.Constants

val android.content.Context.dataStore by preferencesDataStore(name = "lexio_preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LexioTheme {
                LexioApp()
            }
        }
    }
}

@Composable
fun LexioApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val onboardingCompleted by remember {
        context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(Constants.ONBOARDING_COMPLETED_KEY)] ?: false
        }
    }.collectAsState(initial = false)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavScreens = listOf(
        Screen.Home.route,
        "chat",
        Screen.Chat.route,
        Screen.Scenario.route,
        Screen.Library.route,
        Screen.Profile.route
    )

    val showBottomBar = currentRoute in bottomNavScreens ||
            (currentRoute?.startsWith("chat") == true)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navController = navController,
                onboardingCompleted = onboardingCompleted,
            )
        }
    }
}
