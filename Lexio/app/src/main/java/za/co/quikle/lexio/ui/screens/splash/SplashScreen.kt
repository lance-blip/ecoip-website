package za.co.quikle.lexio.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import za.co.quikle.lexio.ui.theme.PrimaryGreen
import za.co.quikle.lexio.ui.theme.SecondaryGold
import za.co.quikle.lexio.ui.theme.TextOnPrimary

@Composable
fun SplashScreen(onNavigateNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000L)
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Lexio",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 56.sp
                ),
                color = TextOnPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Know where you stand.",
                style = MaterialTheme.typography.bodyLarge,
                color = SecondaryGold
            )
            Spacer(modifier = Modifier.height(48.dp))
            CircularProgressIndicator(
                color = TextOnPrimary,
                strokeWidth = 3.dp
            )
        }
    }
}
