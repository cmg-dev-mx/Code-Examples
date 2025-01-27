package mx.dev.cmg.android.jetai.authentication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.jetai.R
import mx.dev.cmg.android.jetai.authentication.register.itemSpacing

@Composable
fun AlternativeLoginOptions(
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Or Sign in with")
        Spacer(modifier = Modifier.height(itemSpacing))
        OutlinedButton(
            onClick = onIconClick,
        ) {
            Text("Google")
            Spacer(modifier = Modifier.height(itemSpacing))
            Icon(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "login with Google",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(itemSpacing))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            Spacer(modifier = Modifier.size(itemSpacing))
            TextButton(onSignUpClick) {
                Text("Sign up")
            }
        }
    }
}