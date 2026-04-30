package za.co.quikle.lexio.ui.screens.premium

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
fun PremiumScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            LexioTopBar(
                title = "Pro Citizen",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Premium Screen\nUpgrade to Pro Citizen\nAd-free • Unlimited queries • Document templates\nR59/month or R499/year",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
