package mx.dev.cmg.android.jobscheduler.core.usecase.onedaylogin

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import javax.inject.Inject

class OneDayWithoutLoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val notificationRepository: NotificationRepository
) : OneDayWithoutLoginUseCase {

    override suspend fun validateOneDayWithoutLoginNotification() =
        loginRepository.isLoggedIn().map { isLoggedIn ->
            if (!isLoggedIn) {
                notificationRepository.setupOneDayNotification().single()
            } else {
                false
            }

        }

    override suspend fun stopJob() = flow {
        val result = notificationRepository.stopJob().single()
        emit(result)
    }
}