package mx.dev.cmg.android.jobscheduler.repository.impl.login

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.cmg.android.jobscheduler.core.repository.LoginRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginRepositoryModule {

    @Binds
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}