package mx.dev.cmg.android.jobscheduler.core.usecase.welcome

import kotlinx.coroutines.flow.map
import mx.dev.cmg.android.jobscheduler.core.repository.NotificationRepository
import javax.inject.Inject

class WelcomeNotificationUseCaseImpl @Inject constructor(
    private val repository: NotificationRepository
): WelcomeNotificationUseCase {

    override suspend fun validateWelcomeNotification() =
        repository.isWelcomeNotificationAlreadyShown().map { alreadyShown ->
            if (!alreadyShown) {
                repository.setWelcomeNotificationShown()
            }
            alreadyShown
        }
}