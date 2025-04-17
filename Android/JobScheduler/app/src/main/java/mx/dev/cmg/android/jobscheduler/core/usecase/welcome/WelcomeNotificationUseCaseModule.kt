package mx.dev.cmg.android.jobscheduler.core.usecase.welcome

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WelcomeNotificationUseCaseModule {

    @Binds
    abstract fun bindWelcomeNotificationUseCase(
        impl: WelcomeNotificationUseCaseImpl
    ): WelcomeNotificationUseCase
}