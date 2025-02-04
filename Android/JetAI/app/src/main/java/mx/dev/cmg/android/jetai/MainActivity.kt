package mx.dev.cmg.android.jetai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.dev.cmg.android.jetai.authentication.login.LoginViewModel
import mx.dev.cmg.android.jetai.chatroom.ChatRoomViewModel
import mx.dev.cmg.android.jetai.chatroom.components.BottomNavBar
import mx.dev.cmg.android.jetai.navigation.JetAiNavGraph
import mx.dev.cmg.android.jetai.navigation.JetAiNavigationActions
import mx.dev.cmg.android.jetai.navigation.Route
import mx.dev.cmg.android.jetai.navigation.Tabs
import mx.dev.cmg.android.jetai.ui.theme.JetAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetAITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetAiApp()
                }
            }
        }
    }

    @Composable
    fun JetAiApp() {
        val navController = rememberNavController()
        val navAction = remember {
            JetAiNavigationActions(navController = navController)
        }
        val loginViewModel: LoginViewModel = viewModel()
        val chatRoomViewModel: ChatRoomViewModel = viewModel()

        val authState by remember { mutableStateOf(loginViewModel.hasUserVerified()) }

        val startRoute = if (authState) Route.NESTED_HOME_ROUTE else Route.NESTED_AUTH_ROUTE
        val stateDestination by navController.currentBackStackEntryAsState()

        val currentDestination = stateDestination?.destination?.route
        val isChatRoomDestination = currentDestination == Tabs.Chats.title
        val isHomeDestination = currentDestination == Route.NESTED_HOME_ROUTE
                || currentDestination == Tabs.Chats.title
                || currentDestination == Tabs.VisualIq.title

        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val tabs = Tabs.entries.toList()

        LaunchedEffect(stateDestination) {
            when(currentDestination) {
                Tabs.Chats.title -> selectedTabIndex = 0
                Tabs.VisualIq.title -> selectedTabIndex = 1
            }
        }

        Scaffold(
            bottomBar = {
                AnimatedVisibility(isHomeDestination) {
                    BottomNavBar(
                        tabs = tabs,
                        selectedIndex = selectedTabIndex,
                        onSelectedChanged = { index ->
                            selectedTabIndex = index
                            when(index) {
                                0 -> navAction.navigateToHomeGraph()
                                1 -> navAction.navigateToVisualIqScreen
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            JetAiNavGraph(
                navController = navController,
                navAction = navAction,
                loginViewModel = loginViewModel,
                startDestination = Route.NESTED_AUTH_ROUTE,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}