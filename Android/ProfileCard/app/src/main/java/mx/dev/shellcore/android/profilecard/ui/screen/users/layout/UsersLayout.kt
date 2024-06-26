package mx.dev.shellcore.android.profilecard.ui.screen.users.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.dev.shellcore.android.profilecard.core.model.UserProfile
import mx.dev.shellcore.android.profilecard.ui.common.AppBar
import mx.dev.shellcore.android.profilecard.ui.common.ProfileContent
import mx.dev.shellcore.android.profilecard.ui.common.ProfilePicture
import mx.dev.shellcore.android.profilecard.ui.route.UserDetailScreen
import mx.dev.shellcore.android.profilecard.ui.screen.users.vm.UsersViewModel

@Composable
fun UsersLayout(navController: NavController? = null) {

    val vm: UsersViewModel = hiltViewModel()
    val users = vm.users.collectAsState().value

    LaunchedEffect(key1 = null) {
        vm.loadUsers()
    }

    UsersLayoutContainer(
        users = users,
        onProfileClick = {
            navController?.navigate(UserDetailScreen(it.id))
        }
    )
}

@Composable
private fun UsersLayoutContainer(
    users: List<UserProfile> = arrayListOf(),
    onProfileClick: (UserProfile) -> Unit = { }
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(users) { user ->
                ProfileCard(user = user, onProfileClick = { onProfileClick(user) })
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
                user = user,
            )
        }
    }
}
