package mx.dev.cmg.android.jetai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import mx.dev.cmg.android.jetai.authentication.login.LoginScreen
import mx.dev.cmg.android.jetai.ui.theme.JetAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetAITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    SignUpScreen(
//                        onLoginClick = {},
//                        onNavigateToLoginScreen = {},
//                        onBackButtonClicked = {},
//                        modifier = Modifier.padding(innerPadding).fillMaxSize()
//                    )
                    LoginScreen(
                        isVerificationEmailSent = false,
                        onSignUpClick = {},
                        onForgotPassword = {},
                        navigateToHomeScreen = {},
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}