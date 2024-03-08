package mx.dev.shellcore.android.dynamiccontent.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GraphRoute.MainGraph.route) {
        mainGraph()
    }
}