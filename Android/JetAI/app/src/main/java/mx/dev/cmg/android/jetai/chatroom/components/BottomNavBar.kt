package mx.dev.cmg.android.jetai.chatroom.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mx.dev.cmg.android.jetai.navigation.Tabs

@Composable
fun BottomNavBar(
    tabs: List<Tabs>,
    selectedIndex: Int,
    onSelectedChanged: (Int) -> Unit
) {

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = index == selectedIndex,
                onClick = { onSelectedChanged(index) },
                icon = {
                    Icon(imageVector = tab.icon, contentDescription = tab.title)
                },
                label = {
                    Text(text = tab.title)
                }
            )
        }
    }

}