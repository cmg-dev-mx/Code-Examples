package mx.dev.cmg.android.jobscheduler.core.usecase.login

import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
): LoginUseCase {

    override suspend fun login() = repository.login()
}