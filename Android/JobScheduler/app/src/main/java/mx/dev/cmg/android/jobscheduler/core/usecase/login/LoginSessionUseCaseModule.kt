package mx.dev.cmg.android.jobscheduler.core.usecase.login

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginSessionUseCaseModule {

    @Binds
    abstract fun bindLoginSessionUseCase(
        loginSessionUseCaseImpl: LoginSessionUseCaseImpl
    ): LoginSessionUseCase
}