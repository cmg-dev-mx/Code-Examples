package mx.dev.cmg.android.jobscheduler.core.usecase.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val notificationRepository: NotificationRepository
): LoginUseCase {

    override suspend fun login(): Flow<Boolean> {
        return loginRepository.login().map { loginSuccess ->
            if (loginSuccess) {
                notificationRepository.stopJob(1)
                notificationRepository.setOneDayNotificationShown()
            }
            true
        }
    }
}