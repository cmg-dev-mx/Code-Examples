package mx.dev.cmg.android.jobscheduler.core.usecase.login

import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import javax.inject.Inject

class LoginSessionUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
): LoginSessionUseCase {

    override suspend fun isLoggedIn() = repository.isLoggedIn()
}