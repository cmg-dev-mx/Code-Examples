package mx.dev.cmg.android.jetai.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import mx.dev.cmg.android.jetai.authentication.forgotpassword.ForgotPasswordScreen
import mx.dev.cmg.android.jetai.authentication.login.LoginScreen
import mx.dev.cmg.android.jetai.authentication.login.LoginViewModel
import mx.dev.cmg.android.jetai.authentication.register.SignUpScreen
import mx.dev.cmg.android.jetai.chatroom.ChatRoomScreen
import mx.dev.cmg.android.jetai.chatroom.ChatRoomViewModel
import mx.dev.cmg.android.jetai.chatroom.EMPTY_TITLE
import mx.dev.cmg.android.jetai.message.MessageScreen
import mx.dev.cmg.android.jetai.message.MessageViewModel
import mx.dev.cmg.android.jetai.message.MessageViewModelFactory
import mx.dev.cmg.android.jetai.photo_reasoning.PhotoReasoningScreen

@Composable
fun JetAiNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navAction: JetAiNavigationActions,
    loginViewModel: LoginViewModel,
    chatRoomViewModel: ChatRoomViewModel,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            navAction = navAction,
            navController = navController,
            loginViewModel = loginViewModel,
            modifier = modifier
        )
        homeGraph(
            navAction = navAction,
            navController = navController,
            chatRoomViewModel = chatRoomViewModel,
            modifier = modifier
        )
    }
}

fun NavGraphBuilder.authGraph(
    navAction: JetAiNavigationActions,
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    modifier: Modifier
) {
    navigation(
        startDestination = Route.LoginScreen().routeWithArgs,
        route = Route.NESTED_AUTH_ROUTE,
    ) {
        composable(
            route = Route.LoginScreen().routeWithArgs,
            arguments = listOf(
                navArgument(name = Route.isEmailSentArg) {}
            )
        ) { entry ->
            LoginScreen(
                onSignUpClick = {
                    navAction.navigateToSignUpScreen()
                },
                isVerificationEmailSent = entry.arguments?.getString(Route.isEmailSentArg)
                    .toBoolean(),
                onForgotPassword = {
                    navAction.navigateToForgotPasswordScreen()
                },
                navigateToHomeScreen = {
                    navAction.navigateToHomeGraph()
                },
                modifier = modifier,
                viewModel = loginViewModel
            )
        }

        composable(
            route = Route.SignupScreen().route
        ) {
            SignUpScreen(
                onLoginClick = {
                    navAction.navigateToLoginScreenWithArgs(false)
                },
                onNavigateToLoginScreen = {
                    navAction.navigateToLoginScreenWithArgs(it)
                },
                onBackButtonClicked = {
                    navAction.navigateToLoginScreenWithArgs(false)
                },
                modifier = modifier,
            )
        }

        composable(
            route = Route.ForgotPasswordScreen().route
        ) {
            ForgotPasswordScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}

fun NavGraphBuilder.homeGraph(
    navAction: JetAiNavigationActions,
    navController: NavHostController,
    chatRoomViewModel: ChatRoomViewModel,
    modifier: Modifier = Modifier,
) {
    val messageRoute = "${Route.MessageScreen().route}/{chatId}/{chatTitle}"

    navigation(startDestination = Tabs.Chats.title, route = Route.NESTED_HOME_ROUTE) {
        composable(route = Tabs.Chats.title) {
            ChatRoomScreen(
                modifier = modifier,
                chatRoomViewModel = chatRoomViewModel
            ) { id, chatTitle ->
                navController.navigate("${Route.MessageScreen().route}/$id/$chatTitle") {
                    launchSingleTop = true
                    popUpTo(Route.MessageScreen().route) { saveState = true }
                    restoreState = true
                }
            }
        }
        composable(
            route = Tabs.VisualIq.title,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            PhotoReasoningScreen(
                modifier = modifier
            )
        }
        composable(
            route = messageRoute,
            arguments = listOf(
                navArgument("chatId") {},
                navArgument("chatTitle") {}
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("chatId") ?: ""
            val title = navBackStackEntry.arguments?.getString("chatTitle") ?: EMPTY_TITLE
            val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(id, title))
            MessageScreen(
                modifier = modifier,
                messageViewModel = viewModel
            )

        }
    }
}