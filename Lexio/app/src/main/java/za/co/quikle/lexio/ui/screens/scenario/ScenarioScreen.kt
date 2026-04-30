package za.co.quikle.lexio.ui.screens.scenario

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import za.co.quikle.lexio.ui.components.LexioTopBar

@Composable
fun ScenarioScreen(navController: NavHostController) {
    Scaffold(
        topBar = { LexioTopBar(title = "Is This Legal?") }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Is This Legal? Screen\nDescribe a situation and get a legal analysis\nApplicable Laws • Analysis • Your Rights • Next Steps",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
