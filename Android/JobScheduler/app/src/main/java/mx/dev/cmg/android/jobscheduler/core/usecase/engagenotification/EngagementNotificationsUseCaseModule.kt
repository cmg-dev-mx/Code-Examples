package mx.dev.cmg.android.jobscheduler.core.usecase.engagenotification

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EngagementNotificationsUseCaseModule {

    @Binds
    abstract fun bindEngagementNotificationsUseCase(
        impl: EngagementNotificationsUseCaseImpl
    ): EngagementNotificationsUseCase
}