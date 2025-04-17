package mx.dev.cmg.android.jobscheduler.core.usecase.onedaylogin

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OneDayWithoutLoginUseCaseModule {

    @Binds
    abstract fun bindOneDayWithoutLoginUseCase(
        impl: OneDayWithoutLoginUseCaseImpl
    ): OneDayWithoutLoginUseCase
}