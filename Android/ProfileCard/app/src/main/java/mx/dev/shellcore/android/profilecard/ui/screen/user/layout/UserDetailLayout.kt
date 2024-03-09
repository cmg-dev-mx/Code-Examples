package mx.dev.shellcore.android.profilecard.ui.screen.user.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.dev.shellcore.android.profilecard.core.model.UserProfile
import mx.dev.shellcore.android.profilecard.ui.common.AppBar
import mx.dev.shellcore.android.profilecard.ui.common.ProfileContent
import mx.dev.shellcore.android.profilecard.ui.common.ProfilePicture

@Composable
fun UserDetailLayout(navController: NavController? = null) {
    val user = UserProfile(
        id = 1,
        name = "Michaela Runnings",
        status = true,
        "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
    )

    UserContainer(
        user = user,
        onBackClick = { /* TODO Not yet implemented */ }
    )
}

@Preview
@Composable
private fun USerDetailLayoutPreview() {
    val user = UserProfile(
        id = 1,
        name = "Michaela Runnings",
        status = true,
        "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
    )

    UserContainer(user = user) {}
}

@Composable
fun UserContainer(
    user: UserProfile,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = "User Detail",
                icon = Icons.Default.ArrowBack,
                onIconClick = onBackClick
            )
        }
    ) { parentPadding ->
        Column(
            modifier = Modifier
                .padding(parentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePicture(
                modifier = Modifier.wrapContentWidth(),
                user = user,
                pictureSize = 240.dp
            )

            ProfileContent(
                modifier = Modifier.wrapContentWidth(),
                user = user,
                alignment = Alignment.CenterHorizontally
            )
        }
    }
}