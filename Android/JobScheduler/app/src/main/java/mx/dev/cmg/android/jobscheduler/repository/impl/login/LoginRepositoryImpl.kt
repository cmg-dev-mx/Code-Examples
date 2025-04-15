package mx.dev.cmg.android.jobscheduler.repository.impl.login

import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource
) : LoginRepository {

    override suspend fun login() = dataStoreSource.setLogin(true)

    override suspend fun isLoggedIn() = dataStoreSource.isLoggedIn()
}