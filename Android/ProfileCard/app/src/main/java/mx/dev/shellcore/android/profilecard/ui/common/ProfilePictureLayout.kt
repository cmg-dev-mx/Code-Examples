package mx.dev.shellcore.android.profilecard.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import mx.dev.shellcore.android.profilecard.core.model.UserProfile

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    user: UserProfile,
    pictureSize: Dp = 64.dp
) {
    val borderColor =
        if (user.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(2.dp, borderColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = rememberImagePainter(user.drawableUrl),
            contentDescription = null,
            modifier = Modifier.size(pictureSize),
            contentScale = ContentScale.Crop
        )
    }
}