package mx.dev.shellcore.android.profilecard.ui.screen.users.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import mx.dev.shellcore.android.profilecard.core.model.UserProfile
import mx.dev.shellcore.android.profilecard.ui.common.AppBar

@Composable
fun MainLayout() {
    val users = arrayListOf<UserProfile>()
    MainLayoutContainer(users)
}

@Composable
private fun MainLayoutContainer(
    users: ArrayList<UserProfile> = arrayListOf(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar("Users", Icons.Default.Home) {}
        }
    ) { parentPaddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(parentPaddingValues)
                .fillMaxSize()
        ) {
            items(users) { user ->
                ProfileCard(user = user, onProfileClick = { /* TODO Not yet implemented */ })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreview() {
    val user = UserProfile(
        id = 1,
        name = "John Doe",
        status = true,
        drawableUrl = "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
    )
    ProfileCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        user = user
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreviewFalse() {
    val user = UserProfile(
        id = 1,
        name = "John Doe",
        status = false,
        drawableUrl = "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
    )
    ProfileCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        user = user
    )
}

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    user: UserProfile,
    onProfileClick: (UserProfile) -> Unit = { }
) {
    Card(
        modifier = modifier.clickable { onProfileClick(user) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(
                modifier = Modifier
                    .wrapContentSize(),
                user = user,
                pictureSize = 72.dp
            )

            ProfileContent(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(16.dp),
                user = user
            )
        }
    }
}

@Composable
private fun ProfilePicture(
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

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    user: UserProfile
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
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
