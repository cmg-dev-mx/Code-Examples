package mx.dev.shellcore.android.profilecard.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun AppBarPreview() {
    Scaffold(
        topBar = {
            AppBar("Title", Icons.Default.Home) {}
        }
    ) { paddingValues ->
        Text("Content", modifier = Modifier.padding(paddingValues))
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    icon: ImageVector,
    onIconClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { onIconClick() },
                imageVector = icon,
                contentDescription = null
            )
        },
        title = {
            Text(text = title)
        }
    )
}