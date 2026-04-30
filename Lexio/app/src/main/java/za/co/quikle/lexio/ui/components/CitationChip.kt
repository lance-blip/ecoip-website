package za.co.quikle.lexio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import za.co.quikle.lexio.ui.theme.CitationBackground
import za.co.quikle.lexio.ui.theme.SecondaryGoldDark

@Composable
fun CitationChip(
    reference: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Text(
        text = reference,
        style = MaterialTheme.typography.labelMedium.copy(
            fontFamily = FontFamily.Monospace
        ),
        color = SecondaryGoldDark,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CitationBackground)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}
