package mx.dev.cmg.android.jobscheduler.core.usecase.onedaylogin

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import javax.inject.Inject

class OneDayWithoutLoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val notificationRepository: NotificationRepository
) : OneDayWithoutLoginUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun validateOneDayWithoutLoginNotification(): Flow<Boolean> {
        Log.d("MOGC", "validateOneDayWithoutLoginNotification")
        return notificationRepository.isOneDayWithoutLoginNotificationAlreadyShown()
            .flatMapConcat OneDayShown@ { isShown ->
                Log.d("MOGC", "OneDayWithoutLogin notification already shown: $isShown")
                if (isShown) return@OneDayShown flow { emit(true) }

                loginRepository.isLoggedIn().flatMapConcat LoggedIn@ { isLoggedIn ->
                    Log.d("MOGC", "isLoggedIn: $isLoggedIn")
                    if (isLoggedIn) return@LoggedIn flow { emit(false) }

                    notificationRepository.setupOneDayNotification().flatMapConcat { success ->
                        Log.d("MOGC", "setupOneDayNotification: $success")
                        if (success) notificationRepository.setOneDayNotificationShown()
                        flow { emit(success) }
                    }
                }
            }
    }

    override suspend fun stopJob() = flow {
        val result = notificationRepository.stopJob(1).last()
        emit(result)
    }
}