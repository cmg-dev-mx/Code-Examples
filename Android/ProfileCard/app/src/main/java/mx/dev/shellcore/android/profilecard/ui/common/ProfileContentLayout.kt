package mx.dev.shellcore.android.profilecard.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mx.dev.shellcore.android.profilecard.core.model.UserProfile

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    user: UserProfile,
    alignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        val color = MaterialTheme.colorScheme.onSurface.copy(
            alpha = if (user.status) 1f else 0.5f
        )

        Text(
            text = user.name,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = if (user.status) "Online" else "Offline",
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
    }
}