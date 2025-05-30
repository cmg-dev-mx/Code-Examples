package mx.dev.cmg.android.jobscheduler.ui.screen.main.layout

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import mx.dev.cmg.android.jobscheduler.R
import mx.dev.cmg.android.jobscheduler.ui.screen.main.state.MainEvent
import mx.dev.cmg.android.jobscheduler.ui.screen.main.vm.MainViewModel
import mx.dev.cmg.android.jobscheduler.ui.theme.JobSchedulerTheme
import mx.dev.cmg.android.jobscheduler.utils.notifications.createNotification

@Preview
@Composable
private fun MainLayoutPreview() {
    JobSchedulerTheme {
        MainLayoutScreen(
            isLoadingState = true
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainLayout() {
    val context = LocalContext.current

    val vm = hiltViewModel<MainViewModel>()
    val state = vm.layoutState.value
    val notificationState = vm.notificationState.value

    val notificationPermissionState =
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS) {
            vm.onEvent(MainEvent.ValidateNotificationWelcome)
        }

    LaunchedEffect(notificationState) {
        Log.d(
            "MOGC",
            "MainLayout show welcome notification: ${notificationState.showWelcomeNotification}"
        )
        if (notificationState.showWelcomeNotification) {
            context.createNotification(
                title = "Job Scheduler",
                content = "Welcome message on app init.",
            )
            vm.onEvent(MainEvent.ValidateLoginSession)
            vm.onEvent(MainEvent.ValidateNotificationOneDayLogin)
        }
    }

    LaunchedEffect(null) {
        if (!notificationPermissionState.status.isGranted) {
            notificationPermissionState.launchPermissionRequest()
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        Log.d("MOGC", "MainLayout: ON_START")
        vm.onEvent(MainEvent.StopEngagementNotifications)
    }

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        Log.d("MOGC", "MainLayout: ON_STOP")
        vm.onEvent(MainEvent.StartEngagementNotifications)
    }

    MainLayoutScreen(
        isLoadingState = state.isLoading,
        isLogged = state.isUserLoggedIn,
        isButtonEnabled = state.isLoginButtonEnabled,
        onLogin = {
            vm.onEvent(MainEvent.OnLoginButtonClick)
        },
        onStopJob = {
            vm.onEvent(MainEvent.OnStopJobButtonClick)
        }
    )
}

@Composable
private fun MainLayoutScreen(
    isLoadingState: Boolean = false,
    isLogged: Boolean = false,
    isButtonEnabled: Boolean = true,
    onLogin: () -> Unit = {},
    onStopJob: () -> Unit = {},
) {
    val loginStatus = if (isLogged) {
        stringResource(R.string.user_logged_in)
    } else {
        stringResource(R.string.not_logged_yet)
    }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(R.string.job_scheduler),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(stringResource(R.string.login_status, loginStatus))

        Button(
            modifier = Modifier.wrapContentWidth(),
            enabled = isButtonEnabled,
            onClick = onLogin,
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Login")

                if (isLoadingState) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Button(
            modifier = Modifier.wrapContentWidth(),
            onClick = onStopJob
        ) {
            Text("Stop Job")
        }
    }
}