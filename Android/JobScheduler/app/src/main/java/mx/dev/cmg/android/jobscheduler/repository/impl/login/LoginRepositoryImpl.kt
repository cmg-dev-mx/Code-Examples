package mx.dev.cmg.android.jobscheduler.repository.impl.login

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository
import mx.dev.cmg.android.jobscheduler.repository.source.DataStoreSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource
) : LoginRepository {

    override suspend fun login(): Flow<Boolean> {
        return dataStoreSource.setLogin(true)
    }

    override suspend fun isLoggedIn(): Flow<Boolean> {
        return dataStoreSource.isLoggedIn()
    }
}